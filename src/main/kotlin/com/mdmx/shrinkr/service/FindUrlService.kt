package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.repository.ShortUrlRepository
import org.springframework.stereotype.Component

@Component
class FindUrlService(
  private val shortUrlRepository: ShortUrlRepository
) {
  fun findOriginalUrl(shortUrl: String): String {
    val shortUrlModel = shortUrlRepository.findOneByPaddedShortUrl(shortUrl)

    return shortUrlModel.originalUrl
  }
}