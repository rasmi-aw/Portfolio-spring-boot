package com.beastwall.portfoliospringboot;

import com.beastwall.portfoliospringboot.model.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication

@RequestMapping("/")
@Controller
public class PortfolioSpringBootApplication {
    private HashMap<String, Data> data;


    public static void main(String[] args) {
        SpringApplication.run(PortfolioSpringBootApplication.class, args);
    }


    @RequestMapping("/")
    public String root(HttpServletRequest request,
                       @RequestParam(required = false) String lang,
                       Map<String, Object> input) throws IOException {

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
        input.put("data", data.get(lang));
        input.put("lang", lang);
        input.put("dir", !lang.equals("ar") ? "ltr" : "rtl");
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

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest request,
                                HttpServletResponse response,
                                Exception e) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
        e.printStackTrace();
    }

}
