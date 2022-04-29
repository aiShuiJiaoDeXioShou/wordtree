package com.wordtree

import javafx.application.Application
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Wordtree
fun main(args: Array<String>) {
    val context = runApplication<Wordtree>(*args)
    Application.launch(Coder::class.java)
}
