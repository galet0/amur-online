package com.amur.areas.users.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    @GetMapping("/unauthorized")
    public String getNoAccessPage(){
        return "No Access";
    }
}
