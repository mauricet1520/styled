package com.styledbylovee.styled

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StyledApplication

fun main(args: Array<String>) {
	runApplication<StyledApplication>(*args)
}
