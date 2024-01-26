package com.alea.pokemon.test.unit.model.type

import com.alea.pokemon.model.type.NaturalNumber
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.Int.Companion.MAX_VALUE
import kotlin.Int.Companion.MIN_VALUE
import kotlin.random.Random

class NaturalNumberTest {

    @Test
    fun `should throws an exception when create a NaturalNumber with null`(){
        // given
        val nullValue: Int? = null

        // when
        assertThrows<IllegalArgumentException> {
            NaturalNumber(nullValue)
        }
    }

    @RepeatedTest(3)
    fun `should throws an exception when create a NaturalNumber with negative value`(){
        // given
        // generate a negative value
        val negativeValue = Random.nextInt(MIN_VALUE, 0)

        // when
        assertThrows<IllegalArgumentException> {
            NaturalNumber(negativeValue)
        }
    }

    @RepeatedTest(3)
    fun `should create a natural number with positive value`(){
        // given
        // generate a positive value
        val positiveValue = Random.nextInt(1, MAX_VALUE)

        // when
        val naturalNumber = NaturalNumber(positiveValue)

        // then
        assert(naturalNumber.toInt() == positiveValue)
        assert(naturalNumber.toLong() == positiveValue.toLong())
        assert(naturalNumber.toFloat() == positiveValue.toFloat())
        assert(naturalNumber.toDouble() == positiveValue.toDouble())
    }

    @RepeatedTest(3)
    fun `should create a natural number with a positive value in String`(){
        // given
        // generate a positive value
        val positiveValue = Random.nextInt(1, MAX_VALUE)

        // when
        val naturalNumber = NaturalNumber(positiveValue.toString())

        // then
        assert(naturalNumber.toInt() == positiveValue)
        assert(naturalNumber.toLong() == positiveValue.toLong())
        assert(naturalNumber.toFloat() == positiveValue.toFloat())
        assert(naturalNumber.toDouble() == positiveValue.toDouble())
    }

    @Test
    fun `should compare two whole numbers`(){
        // given
        val firstWholeNumber = NaturalNumber(1)
        val secondWholeNumber = NaturalNumber(2)

        // when
        val result = firstWholeNumber.compareTo(secondWholeNumber)

        // then
        assert(result < 0)
    }

}