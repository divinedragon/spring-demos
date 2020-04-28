package com.divinedragon.resourceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.divinedragon.resourceserver.domain.Dog;
import com.divinedragon.resourceserver.repository.DogRepository;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private final DogRepository repository;

    @Autowired
    public DogController(final DogRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Dog> list() {
        return repository.findAll();
    }
}
