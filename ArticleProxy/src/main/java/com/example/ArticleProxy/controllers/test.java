package com.example.ArticleProxy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class test {
    
    @RequestMapping(value = "/")
    public String test()
    {
        return "Hellow from test!";
    }
    
    @RequestMapping(value = "/arm")
    public String testarm()
    {
        return "Hellow from test Armor!";
    }
    
}
