/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testsecuity.springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author exk
 */
@RestController
@RequestMapping("/people")
public class testController {

    Collection<Person> people = new ArrayList<>();

    public testController() {
        this.people.add(new Person(1L, "elisha", "kalya"));
        this.people.add(new Person(2L, "James", "bond"));
    }

    //Create
    @PostMapping("/")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        person.setId(Long.parseLong(String.valueOf(this.people.size())));
        people.add(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //Get all
    @GetMapping("/")
    public ResponseEntity<Collection<Person>> findAll() {
        return new ResponseEntity<>(this.people, HttpStatus.OK);
    }

    //Get by Id
    @GetMapping("/{Id}")
    public ResponseEntity<Optional<Person>> findById(@PathVariable("Id") Long id) {
        return new ResponseEntity<>(this.people.stream().filter(p -> p.getId() == id).findFirst(), HttpStatus.OK);
    }

    //Update
    @PutMapping("/")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        this.people.stream().filter(p -> p.getId() == person.getId()).findFirst().ifPresent(p -> {
            p.setFirstName(person.getFirstName());
            p.setLastName(person.getLastName());
        });
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}
