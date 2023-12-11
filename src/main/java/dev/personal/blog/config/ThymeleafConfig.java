package dev.personal.blog.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean("text")
    public SpringTemplateEngine textTemplateEngine(){
        ClassLoaderTemplateResolver textResolver = new ClassLoaderTemplateResolver();

        textResolver.setPrefix("/templates/");
        textResolver.setSuffix(".txt");
        textResolver.setCacheable(false);
        textResolver.setTemplateMode(TemplateMode.TEXT);
        textResolver.setCharacterEncoding("UTF-8");

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(textResolver);

        return engine;
    }

    @Primary
    @Bean("html")
    public SpringTemplateEngine htmlTemplateEngine(){
        ClassLoaderTemplateResolver htmlResolver = new ClassLoaderTemplateResolver();

        htmlResolver.setPrefix("/templates/");
        htmlResolver.setSuffix(".html");
        htmlResolver.setCacheable(false);
        htmlResolver.setTemplateMode(TemplateMode.HTML);
        htmlResolver.setCharacterEncoding("UTF-8");

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(htmlResolver);

        return engine;
    }
}
