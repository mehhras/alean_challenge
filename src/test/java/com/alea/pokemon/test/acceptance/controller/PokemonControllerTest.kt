package com.alea.pokemon.test.acceptance.controller

import com.alea.pokemon.model.type.NaturalNumber
import com.alea.pokemon.repository.pokemon.PokemonRepository
import com.alea.pokemon.test.provider.PokemonProvider
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureMockMvc
class PokemonControllerTest {

    @Autowired
    lateinit var pokemonRepository: PokemonRepository

    private var pokemonProvider: PokemonProvider = PokemonProvider()

    @AfterEach
    fun tearDown(@Autowired mvc: MockMvc) {
        pokemonRepository.deleteAll()
    }

    @RepeatedTest(3)
    fun `should crawl`(@Autowired mvc: MockMvc) {
        mvc.perform(post("/pokemons/crawl"))
            .andExpect(status().isOk())
            .andExpect(content().string(""))
    }

    @RepeatedTest(3)
    fun `should find heaviest pokemon`(@Autowired mvc: MockMvc) {
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach(pokemonRepository::save)

        val heaviestPokemon = pokemonRepository.findHeaviest(NaturalNumber(1)).first()

        mvc.perform(get("/pokemons/heaviest").param("size", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(heaviestPokemon.name))
            .andExpect(jsonPath("$[0].weight").value(heaviestPokemon.weight.toString()))
    }

    @RepeatedTest(3)
    fun `should find highest pokemon`(@Autowired mvc: MockMvc){
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach(pokemonRepository::save)

        val highestPokemon = pokemonRepository.findHighest(NaturalNumber(1)).first()

        mvc.perform(get("/pokemons/highest").param("size", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(highestPokemon.name))
            .andExpect(jsonPath("$[0].height").value(highestPokemon.height.toString()))
    }

    @RepeatedTest(3)
    fun `should find pokemon with the most base experience`(@Autowired mvc: MockMvc){
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach(pokemonRepository::save)

        val mostBaseExperiencePokemon = pokemonRepository.findMostBaseExperience(NaturalNumber(1)).first()

        mvc.perform(get("/pokemons/most-base-experience").param("size", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(mostBaseExperiencePokemon.name))
            .andExpect(jsonPath("$[0].baseExperience").value(mostBaseExperiencePokemon.baseExperience.toString()))
    }


}