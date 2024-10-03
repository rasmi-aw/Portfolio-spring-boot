package com.beastwall.portfoliospringboot.config;

import com.beastwall.beastengine.BeastHtmlEngine;
import com.beastwall.beastengine.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
import java.util.Map;

@Configuration
public class BeastEngineConfig implements WebMvcConfigurer {


    @Bean
    @RequestScope
    public Context context() {
        return new Context();
    }

    @Autowired
    private Context context;

    @Bean
    public BeastHtmlEngine beastHtmlEngine() {
        return new BeastHtmlEngine("app");
    }


    @Bean
    public ViewResolver customViewResolver() {
        return new BeastHtmlEngineResolver(beastHtmlEngine(), context);
    }

    @AllArgsConstructor
    static class BeastHtmlEngineResolver implements ViewResolver, Ordered {
        private BeastHtmlEngine engine;

        private Context context;

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
                    //long time = System.currentTimeMillis();
                    String lang = request.getParameter("lang");
                    if (lang == null) {
                        lang = request.getLocale().getLanguage();
                    }
                    context.setLocale(new Locale(lang));
                    String content = engine.processComponent(viewName, context);
                    //System.out.println(System.currentTimeMillis() - time);
                    response.getWriter().write(content);
                }
            };
        }

    }
}