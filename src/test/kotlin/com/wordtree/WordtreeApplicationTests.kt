package com.wordtree

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment

@SpringBootTest
abstract class WordtreeApplicationTests {

    @Autowired
    var env: Environment? = null;
    @Test
    fun contextLoads() {
        val property = env!!.getProperty("he")
        println(property)
    }

}
