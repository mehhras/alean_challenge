package com.alea.pokemon.test.provider

import com.alea.pokemon.model.pokemon.Pokemon
import kotlin.random.Random

class PokemonProvider {

    fun nextPokemon(): Pokemon {
        return Pokemon.builder()
            .id(nextPokemonId())
            .name(nextString())
            .height(nextNaturalNumber())
            .weight(nextWholeNumber())
            .baseExperience(nextNaturalNumber())
            .build()
    }

    fun nextPokemons(): List<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()
        for (i in 1..Random.nextInt(1, 10)) {
            pokemons.add(nextPokemon())
        }
        return pokemons
    }

}