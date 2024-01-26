package com.alea.pokemon.controller;

import com.alea.pokemon.dto.pokemon.PokemonDto;
import com.alea.pokemon.model.type.NaturalNumber;
import com.alea.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping(value = "crawl")
    public ResponseEntity<Void> crawl() {
        log.info("Crawling Pokemons");
        pokemonService.crawl();

        return ok().build();
    }

    @GetMapping("heaviest")
    public ResponseEntity<List<PokemonDto>> readHeaviest(@RequestParam NaturalNumber size) {
        log.info("Getting heaviest Pokemons");

        final List<PokemonDto> list = pokemonService.findHeaviest(size)
                .stream()
                .map(PokemonDto::mk)
                .toList();

        log.info("Heaviest Pokemons: {}", list);

        return ok().body(list);

    }

    @GetMapping("highest")
    public ResponseEntity<List<PokemonDto>> readHighest(@RequestParam NaturalNumber size) {
        log.info("Getting highest Pokemons");

        final List<PokemonDto> list = pokemonService.findHighest(size)
                .stream()
                .map(PokemonDto::mk)
                .toList();

        log.info("Highest Pokemons: {}", list);

        return ok().body(list);
    }

    @GetMapping("most-base-experience")
    public ResponseEntity<List<PokemonDto>> readMostBaseExperiencesPokemons(@RequestParam NaturalNumber size) {
        log.info("Getting most base experiences Pokemons");

        final List<PokemonDto> list = pokemonService.findMostBaseExperience(size)
                .stream()
                .map(PokemonDto::mk)
                .toList();

        log.info("Most base experiences Pokemons: {}", list);

        return ok().body(list);
    }


}
