package com.sciatta.java.spring.mvc;

import org.springframework.web.bind.annotation.*;

/**
 * Created by yangxiaoyu on 2026/9/23<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * HelloController
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/{name1}")
    public String hello1(@PathVariable("name1") String na) { // http://localhost:8080/hello/1
        return "Hello, " + na;
    }

    @GetMapping
    public String hello2(@RequestParam("name2") String na) { // http://localhost:8080/hello?name2=2
        return "Hello, " + na;
    }
}
