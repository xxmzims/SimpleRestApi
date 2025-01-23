package ru.ugrinovich.RestApi.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "year_of_birth")
    @Temporal(TemporalType.DATE)
    private Date yearOfBirth;

    @Column(name = "email")
    private String email;

    public Person(){

    }

    public Person(int id,String email, Date yearOfBirth, String secondName, String firstName) {
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.secondName = secondName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Date yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
