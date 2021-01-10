package com.businesslogic.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {

    @GetMapping("/get-message")
    public String getMessage() {
        return "Hello World";
    }
}
