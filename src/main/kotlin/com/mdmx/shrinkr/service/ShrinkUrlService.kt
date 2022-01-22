package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.ety.req.ShrinkUrlReqEty
import com.mdmx.shrinkr.model.ShortUrlModel
import com.mdmx.shrinkr.repository.ShortUrlRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class ShrinkUrlService(
  private val shortUrlRepository: ShortUrlRepository,
  private val lg: KLogger = KotlinLogging.logger {}
) {
  fun shrinkUrlViaHashids(shrinkUrlReqEty: ShrinkUrlReqEty): String {

    val shortUrlModel = ShortUrlModel(shrinkUrlReqEty.urlToShrink)
    shortUrlRepository.save(shortUrlModel)

    val result = "saved seq: ${shortUrlModel.urlSeq}"
    lg.info { result }
    return result;
  }
}
