package com.jwt8.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configue {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}