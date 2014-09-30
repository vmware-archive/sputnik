package com.sputnik;

import com.sputnik.persistence.Message;
import com.sputnik.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    @Autowired
    MessageRepository repository;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        MessageRepository repository = ctx.getBean(MessageRepository.class);
        repository.save(new Message("Hello", "Welcome to Sputnik"));

    }
}
