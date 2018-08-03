package com.divinedragon.resourceserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String listUser() {
        return "Hello! How are you?";
    }
}
