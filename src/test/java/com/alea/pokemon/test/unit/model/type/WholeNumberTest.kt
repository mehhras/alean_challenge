package com.alea.pokemon.test.unit.model.type

import com.alea.pokemon.model.type.WholeNumber
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class WholeNumberTest {

    @Test
    fun `should throws an exception when create a WholeNumber with null`(){
        // given
        val nullValue: Int? = null

        // when
        assertThrows<IllegalArgumentException> {
            WholeNumber(nullValue)
        }
    }

    @RepeatedTest(3)
    fun `should throws an exception when create a WholeNumber with negative value`(){
        // given
        // generate a negative value
        val negativeValue = Random.nextInt(Int.MIN_VALUE, 0)

        // when
        assertThrows<IllegalArgumentException> {
            WholeNumber(negativeValue)
        }
    }

    @RepeatedTest(3)
    fun `should create a whole number with positive and zero value`(){
        // given
        // generate a positive value
        val positiveValue = Random.nextInt(0, Int.MAX_VALUE)

        // when
        val wholeNumber = WholeNumber(positiveValue)

        // then
        assert(wholeNumber.toInt() == positiveValue)
        assert(wholeNumber.toLong() == positiveValue.toLong())
        assert(wholeNumber.toFloat() == positiveValue.toFloat())
        assert(wholeNumber.toDouble() == positiveValue.toDouble())
    }

    @Test
    fun `should compare two whole numbers`(){
        // given
        val firstWholeNumber = WholeNumber(1)
        val secondWholeNumber = WholeNumber(2)

        // when
        val result = firstWholeNumber.compareTo(secondWholeNumber)

        // then
        assert(result < 0)
    }


}