package com.beastwall.portfoliospringboot;

import com.beastwall.beastengine.Context;
import com.beastwall.portfoliospringboot.model.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootApplication

@RequestMapping("/")
@Controller
public class PortfolioSpringBootApplication {
    private HashMap<String, Data> data;


    @Autowired
    private Context context;

    public static void main(String[] args) {
        SpringApplication.run(PortfolioSpringBootApplication.class, args);
    }


    @RequestMapping("/")
    public String root(HttpServletRequest request,
                       @RequestParam(required = false) String lang) throws IOException {

        lang = lang == null || lang.isBlank() ? request.getLocale().getLanguage() : lang;
        // reading data json files for each language then cache them
        if (data == null)
            synchronized (new Object()) {
                data = new HashMap<>();
                ClassLoader cl = this.getClass().getClassLoader();
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
                Resource[] resources = resolver.getResources("classpath:static/data/*.json");
                for (Resource resource : resources) {
                    Locale locale = new Locale(resource.getFilename().replace(".json", "").trim());
                    String fileContent = new String(new ClassPathResource("static/data/" + locale.getLanguage() + ".json").getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                    Data d = new ObjectMapper().readValue(fileContent, Data.class);
                    data.put(locale.getLanguage(), d);
                }
            }
        if (!data.containsKey(lang))
            lang = "en";
        //
        context.put("yes", "yes");
        context.put("data", data.get(lang));
        context.put("lang", lang);
        context.put("dir", !lang.equals("ar") ? "ltr" : "rtl");
        return "app";
    }

    /**
     * no Error page will be shown
     */
    @RequestMapping("/{anyparam}")
    public void anyParam(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest request,
                                HttpServletResponse response,
                                Exception e) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
        e.printStackTrace();
    }

    public static String random(String prefix, int numberLength) {
        int randomNumber = new Random().nextInt((int) Math.pow(10, numberLength));
        String numberString = String.format("%0" + numberLength + "d", randomNumber);
        return prefix + numberString;
    }

}
