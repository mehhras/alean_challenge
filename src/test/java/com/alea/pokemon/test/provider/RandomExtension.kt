package com.alea.pokemon.test.provider

import com.alea.pokemon.model.type.NaturalNumber
import com.alea.pokemon.model.type.PokemonUrl
import com.alea.pokemon.model.type.WholeNumber
import com.alea.pokemon.model.type.PokemonId
import kotlin.Int.Companion.MAX_VALUE
import kotlin.random.Random

fun nextPokemonId(): PokemonId {
    return PokemonId(nextNaturalNumber().toInt())
}

fun nextWholeNumber(from: WholeNumber = WholeNumber(
    0
), to: WholeNumber = WholeNumber(
    MAX_VALUE
)
): WholeNumber {
    val until = if (to.toInt() == MAX_VALUE) MAX_VALUE else to.toInt() + 1
    val randomPositiveNumber: Int = Random.nextInt(from.toInt(), until)
    return WholeNumber(randomPositiveNumber)
}

fun nextNaturalNumber(from: NaturalNumber = NaturalNumber(
    1
), to: NaturalNumber = NaturalNumber(
    MAX_VALUE
)
): NaturalNumber {
    val until = if (to.toInt() == MAX_VALUE) MAX_VALUE else to.toInt() + 1
    val randomPositiveNumber: Int = Random.nextInt(from.toInt(), until)
    return NaturalNumber(randomPositiveNumber)
}

fun nextPokemonUrl(): PokemonUrl {
    val prefix = "https://pokeapi.co/api/v2/pokemon/"
    val randomPositiveNumber: Int = Random.nextInt(1, 100)
    val url = prefix + randomPositiveNumber.toString()
    return PokemonUrl(url)
}

fun nextPokemonUrls(): List<PokemonUrl> {
    val urls = mutableListOf<PokemonUrl>()
    for (i in 1..Random.nextInt(1, 10)) {
        urls.add(nextPokemonUrl())
    }
    return urls
}

fun nextPokemonUrlForList(offset: WholeNumber = WholeNumber(0), limit: WholeNumber = WholeNumber(20)): PokemonUrl {
    val prefix = "https://pokeapi.co/api/v2/pokemon/?offset="
    val url = prefix + offset.toInt().toString() + "&limit=" + limit.toInt().toString()
    return PokemonUrl(url)
}

val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
fun nextString(len: Int = 10) = (1..len)
    .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
    .joinToString("")