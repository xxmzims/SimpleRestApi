package ru.ugrinovich.RestApi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.RestApi.Util.PersonErrorResponse;
import ru.ugrinovich.RestApi.Util.PersonNotCreatedException;
import ru.ugrinovich.RestApi.Util.PersonNotFoundException;
import ru.ugrinovich.RestApi.models.Person;
import ru.ugrinovich.RestApi.repositories.PeopleRepository;
import ru.ugrinovich.RestApi.services.PersonService;

import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/people")
public class PeopleRestController {

    private final PersonService personService;
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleRestController(PersonService personService, PeopleRepository peopleRepository){
        this.personService = personService;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping()
    public List<Person> getPeople(){
        return personService.findAll(); // Jackson конвертирует эти объекты в Json
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id){

        return personService.findOne(id);
    }

    @PostMapping
            // с помощью аннотации @Valid отлавливаем ошибки и передаем их в bindingResult
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult){
        // с помощью StringBuilder создаем сообщение об ошибке, перебирай ошибки находящиеся в bindingResult
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError fieldError: errors){
                errorMsg.append(fieldError.getField())
                                .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }
            // Бросаем исключение, если случилась ошибка
            throw new PersonNotCreatedException(errorMsg.toString());
        }


        // Если ошибок не было, сохраняем объект в бд и отправляем http ответ с пустым телом и со статусом 200
        personService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // аннотация для того, чтобы ловить ошибки
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e){
        // создаем экземпляр класса ответа об ошибки, передаем в конструктор сообщение об ошибке и текущее время.
        PersonErrorResponse response = new PersonErrorResponse("Person with current id is not found!", System.currentTimeMillis());

        // В HTTP ответе тело ответа(response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // not found 404
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotCreatedException e){
        PersonErrorResponse response = new PersonErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
