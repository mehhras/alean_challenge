package com.alea.pokemon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@Slf4j
@SpringBootApplication
public class PokemonSpringBootApplication {

    public static void main(String[] args) {
        log.info("Starting Pokemon Spring Application");
        run(PokemonSpringBootApplication.class, args);
    }

}
