package com.example.remotedebuggingintellij;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get")
    public String getData(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toString();
    }
}
