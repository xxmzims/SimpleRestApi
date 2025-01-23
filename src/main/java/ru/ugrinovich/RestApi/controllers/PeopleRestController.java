package ru.ugrinovich.RestApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
