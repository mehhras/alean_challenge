package com.alea.pokemon.test.unit.exception

import com.alea.pokemon.exception.ControllerAdvisor
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE

@SpringBootTest
class ControllerAdvisorTest {

    @Autowired
    lateinit var controllerAdvisor: ControllerAdvisor

    @Test
    fun `should return a response entity with status code bad_request when handle MethodArgumentNotValidException`() {
        // When
        val responseEntity = controllerAdvisor.handleMethodArgumentNotValid()

        // Then
        assert(responseEntity.statusCode == BAD_REQUEST)
    }

    @Test
    fun `should return a response entity with status code bad_request when handle UnsupportedOperationException`(){
        // When
        val responseEntity = controllerAdvisor.handleUnsupportedOperationException()

        // Then
        assert(responseEntity.statusCode == BAD_REQUEST)
    }

    @Test
    fun `should return a response entity with status code bad_request when handle IllegalArgumentException`(){
        // When
        val responseEntity = controllerAdvisor.handleIllegalArgumentException()

        // Then
        assert(responseEntity.statusCode == BAD_REQUEST)
    }

    @Test
    fun `should return a response entity with status code ServiceUnavailable when handle UnknownHostException`(){
        // When
        val responseEntity = controllerAdvisor.handleUnknownHostException()

        // Then
        assert(responseEntity.statusCode == SERVICE_UNAVAILABLE)
    }

}