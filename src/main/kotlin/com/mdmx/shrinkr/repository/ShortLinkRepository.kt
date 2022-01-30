package com.mdmx.shrinkr.repository

import com.mdmx.shrinkr.model.ShortLinkModel
import org.springframework.data.repository.CrudRepository

interface ShortLinkRepository : CrudRepository<ShortLinkModel, Long> {
  fun findOneByPaddedShortLink(paddedShortLink: String): ShortLinkModel
  fun findOneByDeactivationCodeAndDeactivatedAtIsNull(deactivationCode: String): ShortLinkModel
}
