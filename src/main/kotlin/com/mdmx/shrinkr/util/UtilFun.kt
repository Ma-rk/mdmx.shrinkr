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

/**
 * https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
 */
fun addPaddingCharacter(originalStr: String, designatedLength: Int): String {
  val alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789"
  if (designatedLength <= originalStr.length) {
    return originalStr
  }
  val loopIndicator = designatedLength - 1
  val sb = StringBuilder(originalStr.length)
  for (i in originalStr.length..loopIndicator) {
    val index = (alphaNumericString.length * Math.random()).toInt()
    sb.append(alphaNumericString[index])
  }
  return originalStr + sb.toString()
}
