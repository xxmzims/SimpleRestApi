package ru.ugrinovich.RestApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ugrinovich.RestApi.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
