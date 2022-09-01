package com.rainsensor.rainsensorrestapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RainSensorRestApiApplication {

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(RainSensorRestApiApplication.class, args);
    }

}
