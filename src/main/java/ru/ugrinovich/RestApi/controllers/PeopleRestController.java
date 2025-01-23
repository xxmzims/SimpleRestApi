package ru.ugrinovich.RestApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.RestApi.Util.PersonErrorResponse;
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

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse("Person with current id is not found!", System.currentTimeMillis());

        // В HTTP ответе тело ответа(response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
