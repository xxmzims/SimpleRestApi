package ru.ugrinovich.RestApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name shouldn't be empty")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Second name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Second name should be between 2 and 30 characters")
    @Column(name = "second_name")
    private String secondName;

    @Column(name = "year_of_birth")
    @Temporal(TemporalType.DATE)
    private Date yearOfBirth;

    @Email
    @NotEmpty(message = "Email shouldn't be empty")
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
