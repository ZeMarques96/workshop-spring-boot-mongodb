package com.zemarques96.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.zemarques96.workshopmongo.entities.User;
import com.zemarques96.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null , "Maria Brown", "maria@mail.com");
        User alex = new User(null , "Alex Green", "alex@mail.com");
        User bob = new User(null , "Bob Grey", "bob@mail.com");

        userRepository.saveAll(Arrays.asList(maria,alex, bob));
    }

}
