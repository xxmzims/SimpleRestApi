package ru.ugrinovich.RestApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ugrinovich.RestApi.Util.PersonNotFoundException;
import ru.ugrinovich.RestApi.models.Person;
import ru.ugrinovich.RestApi.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> personOptional = peopleRepository.findById(id);

        return personOptional.orElseThrow(PersonNotFoundException::new);
    }
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

}
