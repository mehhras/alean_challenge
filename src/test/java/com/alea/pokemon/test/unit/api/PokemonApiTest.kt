package com.alea.pokemon.test.unit.api

import com.alea.pokemon.api.PokemonApi
import com.alea.pokemon.model.pokemon.Pokemon
import com.alea.pokemon.model.type.WholeNumber
import com.alea.pokemon.test.provider.nextPokemonUrl
import com.alea.pokemon.test.provider.nextPokemonUrlForList
import com.alea.pokemon.test.provider.nextWholeNumber
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PokemonApiTest {

    @Autowired
    lateinit var pokemonApi: PokemonApi

    @RepeatedTest(3)
    fun `should get a pokemon`() {
        val pokemon = pokemonApi.getPokemonByURL(nextPokemonUrl())

        assert(pokemon != null)
        assert(pokemon.isPresent)
        assert(pokemon.get().javaClass == Pokemon::class.java)
        assert(pokemon.get().name.isNotEmpty())
    }

    @RepeatedTest(3)
    fun `should get a list of pokemons by a url`(){
        val offset = nextWholeNumber(to = WholeNumber(1300))
        val limit = nextWholeNumber(to = WholeNumber(100))
        val url = nextPokemonUrlForList(offset, limit)
        val pokemonList = pokemonApi.getAllPokemons(url)

        assert(pokemonList != null)
        assert(pokemonList.results().isNotEmpty())

        // assert that if nextUrl is not null, then hasNext must be true, otherwise false
        if(pokemonList.nextUrl() != null){
            assert(pokemonList.hasNext())
            assert(pokemonList.results().size == limit.toInt())
        } else {
            assert(!pokemonList.hasNext())
            assert(pokemonList.results().size <= limit.toInt())
        }

    }

}