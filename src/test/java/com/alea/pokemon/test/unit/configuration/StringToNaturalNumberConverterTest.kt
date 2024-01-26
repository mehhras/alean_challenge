package com.alea.pokemon.test.unit.configuration

import com.alea.pokemon.configuration.StringToNaturalNumberConverter
import com.alea.pokemon.test.provider.nextNaturalNumber
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StringToNaturalNumberConverterTest {

    @Autowired
    lateinit var stringToNaturalNumberConverter: StringToNaturalNumberConverter

    @RepeatedTest(3)
    fun `should convert a string to a natural number`(){
        val randomNaturalNumber = nextNaturalNumber()
        val randomNaturalNumberAsString = randomNaturalNumber.toString()

        val convertedNaturalNumber = stringToNaturalNumberConverter.convert(randomNaturalNumberAsString)

        assert(convertedNaturalNumber != null)
        assert(convertedNaturalNumber == randomNaturalNumber)
    }

}