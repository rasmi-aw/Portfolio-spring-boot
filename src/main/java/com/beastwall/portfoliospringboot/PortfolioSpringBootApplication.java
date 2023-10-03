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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

@SpringBootApplication

@RequestMapping("/")
@Controller
public class PortfolioSpringBootApplication {
    private HashMap<String, Date> data;


    public static void main(String[] args) {
        SpringApplication.run(PortfolioSpringBootApplication.class, args);
    }


    @RequestMapping("/")
    public String root(HttpServletRequest request) throws IOException {
        String lang = request.getLocale().getLanguage();
        // reading data json files for each language then cache them
        if (data == null)
            synchronized (new Object()) {
                data = new HashMap<>();
                ClassLoader cl = this.getClass().getClassLoader();
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
                Resource[] resources = resolver.getResources("classpath:static/data/*.json");
                for (Resource resource : resources) {
                    Locale locale = new Locale(resource.getFilename().replace(".json", "").trim());
                }
               /* String ths = new String(new ClassPathResource("values/config.json").getInputStream().readAllBytes(), StandardCharsets.UTF_8);

                File dataDir = new File()*/
            }
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
                                HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }

}
