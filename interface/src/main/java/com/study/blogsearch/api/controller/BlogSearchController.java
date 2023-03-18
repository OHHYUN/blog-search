package com.study.blogsearch.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogSearchController {

    @GetMapping("/search")
    public String test() {
        return "test";
    }
}
