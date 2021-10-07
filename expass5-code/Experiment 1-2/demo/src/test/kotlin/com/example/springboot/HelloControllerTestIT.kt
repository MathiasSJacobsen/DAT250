package com.example.springboot

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIT {
    @Autowired
    private val template: TestRestTemplate? = null

    @get:Throws(Exception::class)
    @get:Test
    val hello: Unit
        get() {
            val response = template!!.getForEntity("/", String::class.java)
            Assertions.assertThat(response.body).isEqualTo("Greetings from Spring Boot!")
        }
}