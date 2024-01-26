package com.alea.pokemon.test.integration.repository

import com.alea.pokemon.model.type.NaturalNumber
import com.alea.pokemon.repository.pokemon.PokemonEntity
import com.alea.pokemon.repository.pokemon.PokemonEntityRepository
import com.alea.pokemon.repository.pokemon.PokemonRepository
import com.alea.pokemon.test.provider.PokemonProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PokemonRepositoryTest {

    @Autowired
    lateinit var pokemonRepository: PokemonRepository
    private var pokemonProvider: PokemonProvider = PokemonProvider()

    @Autowired
    lateinit var pokemonEntityRepository: PokemonEntityRepository

    @RepeatedTest(3)
    fun `should save pokemon`() {
        val beforeSaved = pokemonEntityRepository.findAll()

        val pokemon = pokemonProvider.nextPokemon()
        pokemonRepository.save(pokemon)

        val allFromDb = pokemonEntityRepository.findAll().map { it.toModel() }

        assertThat(allFromDb).hasSize(beforeSaved.size + 1)
        assertThat(allFromDb).contains(pokemon)
    }

    @RepeatedTest(3)
    fun `should find All Pokemons`(){
        val beforeSaved = pokemonEntityRepository.findAll().map { it.toModel() }
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach{PokemonEntity.mk(it).let(pokemonEntityRepository::save)}

        val allPokemonsFromDb = pokemonRepository.findAll()

        assertThat(allPokemonsFromDb).hasSize(beforeSaved.size + pokemons.size)
        assertThat(allPokemonsFromDb).containsAll(beforeSaved)
        assertThat(allPokemonsFromDb).containsAll(pokemons)
    }

    @RepeatedTest(3)
    fun `should find the heaviest pokemon when there is a pokemon with the heaviest weight`() {
        val beforeSaved = pokemonEntityRepository.findAll().map { it.toModel() }
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach{PokemonEntity.mk(it).let(pokemonEntityRepository::save)}

        // find the heaviest pokemon between beforeSaved and pokemons
        val heaviestPokemon = (beforeSaved + pokemons).maxByOrNull { it.weight }!!

        val heaviestPokemonFromDb = pokemonRepository.findHeaviest(NaturalNumber(1))

        assertThat(heaviestPokemonFromDb).hasSize(1)
        assertThat(heaviestPokemonFromDb[0]).isEqualTo(heaviestPokemon)
    }

    @RepeatedTest(3)
    fun `should find the highest pokemon when there is a pokemon with the highest height`() {
        val beforeSaved = pokemonEntityRepository.findAll().map { it.toModel() }
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach{PokemonEntity.mk(it).let(pokemonEntityRepository::save)}

        // find the highest pokemon between beforeSaved and pokemons
        val highestPokemon = (beforeSaved + pokemons).maxByOrNull { it.height }!!

        val highestPokemonFromDb = pokemonRepository.findHighest(NaturalNumber(1))

        assertThat(highestPokemonFromDb).hasSize(1)
        assertThat(highestPokemonFromDb[0]).isEqualTo(highestPokemon)
    }

    @RepeatedTest(3)
    fun `should find the pokemon with the most base experience when there is a pokemon with the most base experience`(){
        val beforeSaved = pokemonEntityRepository.findAll().map { it.toModel() }
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach{PokemonEntity.mk(it).let(pokemonEntityRepository::save)}

        // find the pokemon with the most base experience between beforeSaved and pokemons
        val mostBaseExperiencePokemon = (beforeSaved + pokemons).maxByOrNull { it.baseExperience }!!

        val mostBaseExperiencePokemonFromDb = pokemonRepository.findMostBaseExperience(NaturalNumber(1))

        assertThat(mostBaseExperiencePokemonFromDb).hasSize(1)
        assertThat(mostBaseExperiencePokemonFromDb[0]).isEqualTo(mostBaseExperiencePokemon)
    }

    @RepeatedTest(3)
    fun `should delete all pokemons`(){
        val pokemons = pokemonProvider.nextPokemons()
        pokemons.forEach{PokemonEntity.mk(it).let(pokemonEntityRepository::save)}

        pokemonRepository.deleteAll()

        val allPokemonsFromDb = pokemonRepository.findAll()

        assertThat(allPokemonsFromDb).isEmpty()
    }


}