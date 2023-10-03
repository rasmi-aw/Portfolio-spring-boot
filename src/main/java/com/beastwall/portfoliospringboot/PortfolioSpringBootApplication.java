package com.beastwall.portfoliospringboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@SpringBootApplication

@RequestMapping("/")
@Controller
public class PortfolioSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioSpringBootApplication.class, args);
    }


    @RequestMapping("/")
    public String root(HttpServletRequest request) {
        String lang = request.getLocale().getLanguage();
        return "index";
    }

    /**
     * no Error page will be shown
     */
    @RequestMapping("/{anyparam}")
    public void anyParam(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }


}
