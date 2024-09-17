package com.beastwall.portfoliospringboot.config;

import com.beastwall.beaststack.engine.BeastHtmlEngine;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
import java.util.Map;

@Configuration
public class BeastEngineConfig implements WebMvcConfigurer {

    @Bean
    public BeastHtmlEngine beastHtmlEngine() {
        return new BeastHtmlEngine();
    }


    @Bean
    public ViewResolver customViewResolver() {
        return new BeastHtmlEngineResolver(beastHtmlEngine());
    }

    @AllArgsConstructor
    class BeastHtmlEngineResolver implements ViewResolver, Ordered {
        private BeastHtmlEngine engine;

        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE;
        }

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return new View() {
                @Override
                public String getContentType() {
                    return "text/html";
                }

                @Override
                public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                    String content = engine.processComponent(viewName, (Map<String, Object>) model);
                    response.getWriter().write(content);
                }
            };
        }

    }
}