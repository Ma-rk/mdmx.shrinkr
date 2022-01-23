package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.repository.ShortUrlRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class FindUrlService(
  private val shortUrlRepository: ShortUrlRepository
) {
  fun findOriginalUrl(shortUrl: String): String {
    return try {
      val shortUrlModel = shortUrlRepository.findOneByPaddedShortUrl(shortUrl)
      shortUrlModel.originalUrl
    } catch (ex: EmptyResultDataAccessException) {
      "https://kotlinlang.org/"
    }
  }
}