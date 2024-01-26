package com.alea.pokemon.repository.pokemon;

import com.alea.pokemon.model.pokemon.Pokemon;
import com.alea.pokemon.model.type.NaturalNumber;

import java.util.List;

public interface PokemonRepository {
    void save(Pokemon pokemon);
    List<Pokemon> findAll();

    void deleteAll();

    List<Pokemon> findHeaviest(NaturalNumber size);

    List<Pokemon> findHighest(NaturalNumber size);

    List<Pokemon> findMostBaseExperience(NaturalNumber size);
}
