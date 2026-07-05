package com.devclicker.dev_clicker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHealth {
    @GetMapping("/health")
    public String health() {
        return "ok";
    }
}
