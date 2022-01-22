package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.ety.req.ShrinkUrlReqEty
import com.mdmx.shrinkr.model.ShortUrlModel
import com.mdmx.shrinkr.repository.ShortUrlRepository
import com.mdmx.shrinkr.util.addPaddingCharacter
import com.mdmx.shrinkr.util.getRandomString
import mu.KLogger
import mu.KotlinLogging
import org.hashids.Hashids
import org.springframework.stereotype.Component

@Component
class ShrinkUrlService(
  private val shortUrlRepository: ShortUrlRepository,
  private val lg: KLogger = KotlinLogging.logger {}
) {
  private val hashids: Hashids = Hashids("my heart draws a dream")

  fun shrinkUrlViaHashids(shrinkUrlReqEty: ShrinkUrlReqEty): String {

    val shortUrlModel = ShortUrlModel(shrinkUrlReqEty.urlToShrink)
    shortUrlRepository.save(shortUrlModel)

    val seq = shortUrlModel.urlSeq
    val hashedUrl = seq?.let { hashids.encode(it) }
    val paddedUrl = hashedUrl?.let { addPaddingCharacter(it, 7) }

    shortUrlModel.shortUrl = hashedUrl
    shortUrlModel.paddedShortUrl = paddedUrl

    if (shrinkUrlReqEty.deletionLinkChecked) {
      shortUrlModel.creatorEmailAddr = shrinkUrlReqEty.emailAddr
      shortUrlModel.deactivationCode = getRandomString(178)
    }

    shortUrlRepository.save(shortUrlModel)

    val result = "saved seq: ${shortUrlModel.urlSeq}"
    lg.info { result }
    return result;
  }
}
