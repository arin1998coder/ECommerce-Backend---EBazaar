package com.BadaBazaar.Ecommerce.controller;

import com.BadaBazaar.Ecommerce.model.Item;
import com.BadaBazaar.Ecommerce.model.Test;
import com.BadaBazaar.Ecommerce.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestRepository testRepository;

    @PostMapping("/add/{id}/{name}")
    public String testBuilder(@PathVariable int id, @PathVariable String name){

        Test t = Test.builder().id(id)
                .name(name)
                .list("bike")
                .list("Car")
                .build();

        testRepository.save(t);

        return "Success";

    }

    @GetMapping("/getItems/{id}")
    public List<String> getItems(@PathVariable int id)
    {
        System.out.println(testRepository.findById(id).get().getList());
        return testRepository.findById(id).get().getList();
    }
}
