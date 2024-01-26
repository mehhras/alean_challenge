package com.alea.pokemon.test.unit.model.type

import com.alea.pokemon.model.type.PokemonUrl
import com.alea.pokemon.test.provider.nextString
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class PokemonUrlTest {

    @RepeatedTest(3)
    fun `should throws an exception when create a PokemonUrl with wrong prefix`(){
        // given
        // generate a string
        val randomString = nextString()

        // when
        assertThrows<IllegalArgumentException> {
            PokemonUrl(randomString)
        }
    }

    @RepeatedTest(3)
    fun `should create a PokemonUrl when correct prefix`(){
        // given
        // generate a positive value
        val prefix = "https://pokeapi.co/api/v2/pokemon/"
        val randomPositiveNumber: Int = Random.nextInt(1, 100)
        val url = prefix + randomPositiveNumber.toString()

        // when
        val pokemonUrl = PokemonUrl(url)

        // then
        assert(pokemonUrl.url() == url)
    }

    @RepeatedTest(3)
    fun `should return length of url`(){
        // given
        // generate a positive value
        val prefix = "https://pokeapi.co/api/v2/pokemon/"
        val randomPositiveNumber: Int = Random.nextInt(1, 100)
        val url = prefix + randomPositiveNumber.toString()

        // when
        val pokemonUrl = PokemonUrl(url)

        // then
        assert(pokemonUrl.length == url.length)
    }

    @RepeatedTest(3)
    fun `should return a substring of url`(){
        // given
        // generate a positive value
        val prefix = "https://pokeapi.co/api/v2/pokemon/"
        val randomPositiveNumber: Int = Random.nextInt(1, 100)
        val url = prefix + randomPositiveNumber.toString()
        val rndLength = Random.nextInt(1, url.length)

        // when
        val pokemonUrl = PokemonUrl(url)

        // then
        assert(pokemonUrl.subSequence(0, rndLength) == url.substring(0, rndLength))
    }

    @RepeatedTest(3)
    fun `should return charAt`(){
        // given
        // generate a positive value
        val prefix = "https://pokeapi.co/api/v2/pokemon/"
        val randomPositiveNumber: Int = Random.nextInt(1, 100)
        val url = prefix + randomPositiveNumber.toString()
        val rndIndex = Random.nextInt(1, url.length)

        // when
        val pokemonUrl = PokemonUrl(url)

        // then
        assert(pokemonUrl[rndIndex] == url[rndIndex])
    }

}