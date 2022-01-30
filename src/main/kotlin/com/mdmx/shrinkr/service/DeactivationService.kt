package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.repository.ShortLinkRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeactivationService(
  private val shortLinkRepository: ShortLinkRepository,
) {
  private val lg: KLogger = KotlinLogging.logger {}

  fun deactivateShortLink(deactivationCode: String): Boolean {
    return try {
      lg.info("checking deactivationCode is in the table... $deactivationCode")
      val shortLinkModel = shortLinkRepository.findOneByDeactivationCodeAndDeactivatedAtIsNull(deactivationCode)
      lg.info("found: $shortLinkModel")
      shortLinkModel.deactivatedAt = LocalDateTime.now()
      shortLinkRepository.save(shortLinkModel)
      true
    } catch (ex: EmptyResultDataAccessException) {
      lg.error { "no such row..." }
      false
    }
  }
}