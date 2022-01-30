package com.mdmx.shrinkr.service

import com.mdmx.shrinkr.ety.req.ShrinkLinkReqEty
import com.mdmx.shrinkr.ety.resp.ShrinkLinkRespEty
import com.mdmx.shrinkr.model.ShortLinkModel
import com.mdmx.shrinkr.repository.ShortLinkRepository
import com.mdmx.shrinkr.util.addPaddingCharacter
import com.mdmx.shrinkr.util.getRandomString
import mu.KLogger
import mu.KotlinLogging
import org.hashids.Hashids
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ShrinkLinkService(
  private val shortLinkRepository: ShortLinkRepository,
  private val lg: KLogger = KotlinLogging.logger {}
) {
  private val hashids: Hashids = Hashids("my heart draws a dream")

  fun shrinkLinkViaHashids(shrinkLinkReqEty: ShrinkLinkReqEty): ShrinkLinkRespEty {
    lg.info { "Got $shrinkLinkReqEty" }

    val shortLinkModel = ShortLinkModel(shrinkLinkReqEty.linkToShrink)
    shortLinkRepository.save(shortLinkModel)

    val seq = shortLinkModel.linkSeq
    val hashedLink = seq?.let { hashids.encode(it) }
    val paddedLink = hashedLink?.let { addPaddingCharacter(it, 7) }

    shortLinkModel.shortLink = hashedLink
    shortLinkModel.paddedShortLink = paddedLink

    if (shrinkLinkReqEty.deletionLinkChecked) {
      shortLinkModel.creatorEmailAddr = shrinkLinkReqEty.emailAddr
      shortLinkModel.deactivationCode = getRandomString(178)
    }

    shortLinkRepository.save(shortLinkModel)

    val muMap = mutableMapOf<String, String?>()
    muMap["linkToShrink"] = shrinkLinkReqEty.linkToShrink
    muMap["shortLink"] = "https://mdmx.xyz/re/$paddedLink"
    if (shrinkLinkReqEty.deletionLinkChecked) {
      muMap["creatorEmailAddr"] = shrinkLinkReqEty.emailAddr
      muMap["deactivationLink"] = shortLinkModel.deactivationCode
    }
    val shrinkLinkRespEty = ShrinkLinkRespEty(
      HttpStatus.OK,
      HttpStatus.OK.value(),
      "SUCCESS. Link has been successfully shrunken",
      muMap
    )
    lg.info("Result of Shrinking: $shrinkLinkRespEty")
    val result = "saved seq: ${shortLinkModel.linkSeq}"
    lg.info { result }
    return shrinkLinkRespEty
  }
}
