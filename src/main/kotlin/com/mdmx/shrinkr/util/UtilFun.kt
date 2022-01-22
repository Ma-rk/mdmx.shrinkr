package com.mdmx.shrinkr.util

import java.util.*


/**
 * https://www.baeldung.com/java-random-string
 */
fun getRandomString(targetStringLength: Long): String {
  val leftLimit = 48 // numeral '0'
  val rightLimit = 122 // letter 'z'
  val random = Random()
  return random.ints(leftLimit, rightLimit + 1)
    .filter { i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) }
    .limit(targetStringLength)
    .collect({ StringBuilder() }, java.lang.StringBuilder::appendCodePoint, java.lang.StringBuilder::append)
    .toString()
}
