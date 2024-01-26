package com.alea.pokemon.service;

import com.alea.pokemon.model.pokemon.Pokemon;
import com.alea.pokemon.model.type.NaturalNumber;
import com.alea.pokemon.model.type.PokemonUrl;

import java.util.List;

public interface PokemonService {
    void crawl();
    List<PokemonUrl> crawlPokemonUrls();
    List<Pokemon> crawlPokemonInfo(List<PokemonUrl> urls);

    void save(Pokemon pokemon);

    List<Pokemon> findHeaviest(NaturalNumber size);

    List<Pokemon> findHighest(NaturalNumber size);

    List<Pokemon> findMostBaseExperience(NaturalNumber size);


}
