package br.com.diogomendes

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.diogomendes")
		.start()
}

