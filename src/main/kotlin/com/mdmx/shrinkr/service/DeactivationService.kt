package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.repository.ShortUrlRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeactivationService(
  private val shortUrlRepository: ShortUrlRepository,
) {
  private val lg: KLogger = KotlinLogging.logger {}

  fun deactivateShortLink(deactivationCode: String): Boolean {
    return try {
      lg.info("checking deactivationCode is in the table... $deactivationCode")
      val shortUrlModel = shortUrlRepository.findOneByDeactivationCodeAndDeactivatedAtIsNull(deactivationCode)
      lg.info("found: $shortUrlModel")
      shortUrlModel.deactivatedAt = LocalDateTime.now()
      shortUrlRepository.save(shortUrlModel)
      true
    } catch (ex: EmptyResultDataAccessException) {
      lg.error { "no such row..." }
      false
    }
  }
}