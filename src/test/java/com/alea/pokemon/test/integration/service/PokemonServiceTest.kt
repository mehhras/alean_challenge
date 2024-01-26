package com.alea.pokemon.test.integration.service

import com.alea.pokemon.model.pokemon.Pokemon
import com.alea.pokemon.model.type.NaturalNumber
import com.alea.pokemon.model.type.PokemonUrl
import com.alea.pokemon.repository.pokemon.PokemonRepository
import com.alea.pokemon.service.PokemonService
import com.alea.pokemon.test.provider.PokemonProvider
import com.alea.pokemon.test.provider.nextPokemonUrls
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PokemonServiceTest {

    @Autowired
    lateinit var pokemonService: PokemonService
    private var pokemonProvider: PokemonProvider = PokemonProvider()

    @Autowired
    lateinit var pokemonRepository: PokemonRepository

    @RepeatedTest(3)
    fun `should save pokemon`() {
        val beforeSaved = pokemonRepository.findAll()

        val pokemon = pokemonProvider.nextPokemon()
        pokemonService.save(pokemon)

        val allFromDb = pokemonRepository.findAll()

        assertThat(allFromDb).hasSize(beforeSaved.size + 1)
        assertThat(allFromDb).contains(pokemon)
    }

    @RepeatedTest(3)
    fun `should find the heaviest pokemon when there is a pokemon with the heaviest weight`() {
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach(pokemonRepository::save)

        val heaviestPokemon = pokemonRepository.findHeaviest(NaturalNumber(1)).first()
        val heaviestPokemonFromService = pokemonService.findHeaviest(NaturalNumber(1))

        assertThat(heaviestPokemonFromService).hasSize(1)
        assertThat(heaviestPokemonFromService[0]).isEqualTo(heaviestPokemon)
    }

    @RepeatedTest(3)
    fun `should find the highest pokemon when there is a pokemon with the highest height`() {
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach(pokemonRepository::save)

        val highestPokemon = pokemonRepository.findHighest(NaturalNumber(1)).first()
        val highestPokemonFromService = pokemonService.findHighest(NaturalNumber(1))

        assertThat(highestPokemonFromService).hasSize(1)
        assertThat(highestPokemonFromService[0]).isEqualTo(highestPokemon)
    }

    @RepeatedTest(3)
    fun `should find the pokemon with the most base experience when there is one with the most base experience`() {
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach(pokemonRepository::save)

        val mostBaseExperiencePokemon = pokemonRepository.findMostBaseExperience(NaturalNumber(1)).first()
        val mostBaseExperiencePokemonFromService = pokemonService.findMostBaseExperience(NaturalNumber(1))

        assertThat(mostBaseExperiencePokemonFromService).hasSize(1)
        assertThat(mostBaseExperiencePokemonFromService[0]).isEqualTo(mostBaseExperiencePokemon)
    }

    @RepeatedTest(3)
    fun `should crawl a list of pokemon urls`() {
        val urls = nextPokemonUrls()
        val crawlPokemonUrls = pokemonService.crawlPokemonInfo(urls)

        assertThat(crawlPokemonUrls).hasSize(urls.size)
    }

    @Test
    fun `should crawl urls`(){
        val urls = pokemonService.crawlPokemonUrls()

        assertThat(urls).isNotEmpty
        assert(urls[0].javaClass == PokemonUrl::class.java)
    }

    @Test
    fun `should crawl`(){
        pokemonRepository.deleteAll()

        pokemonService.crawl()

        val pokemons = pokemonRepository.findAll()
        println(pokemons.size)
        assertThat(pokemons).isNotEmpty
        assert(pokemons[0].javaClass == Pokemon::class.java)
    }
}