package com.beastwall.portfoliospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication

@RequestMapping("/")
@Controller
public class PortfolioSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioSpringBootApplication.class, args);
    }


    @RequestMapping("/")
    public String root() {
        return "index";
    }
}
