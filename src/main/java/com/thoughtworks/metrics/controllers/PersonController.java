package com.thoughtworks.metrics.controllers;

import com.thoughtworks.metrics.models.Person;
import com.thoughtworks.metrics.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value ="/people", method = RequestMethod.GET)
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
