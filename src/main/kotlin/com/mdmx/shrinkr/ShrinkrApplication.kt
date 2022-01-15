package com.mdmx.shrinkr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShrinkrApplication

fun main(args: Array<String>) {
  runApplication<ShrinkrApplication>(*args)
}
