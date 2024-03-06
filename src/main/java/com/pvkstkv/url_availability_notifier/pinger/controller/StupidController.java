package com.pvkstkv.url_availability_notifier.pinger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StupidController {
    @GetMapping("/")
    public String d(){
        return "Fucking hello";
    }
}
