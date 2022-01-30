package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.repository.ShortLinkRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class FindLinkService(
  private val shortLinkRepository: ShortLinkRepository
) {
  fun findOriginalLink(shortLink: String): String {
    return try {
      val shortLinkModel = shortLinkRepository.findOneByPaddedShortLink(shortLink)
      shortLinkModel.originalLink
    } catch (ex: EmptyResultDataAccessException) {
      "https://kotlinlang.org/"
    }
  }
}