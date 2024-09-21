package com.rest.webservices.rest_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldContoller {

    private MessageSource messageSource;

    public HelloWorldContoller(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @GetMapping("hello")
    public HelloWorldBean helloWorld() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World %s", name));
    }

    @GetMapping("hello-international")
    public String helloWorldInternationlized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
