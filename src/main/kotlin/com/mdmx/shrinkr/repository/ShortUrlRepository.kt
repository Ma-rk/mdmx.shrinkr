package com.mdmx.shrinkr.repository

import com.mdmx.shrinkr.model.ShortUrlModel
import org.springframework.data.repository.CrudRepository

interface ShortUrlRepository : CrudRepository<ShortUrlModel, Long> {
  fun findOneByPaddedShortUrl(padded_short_url: String): ShortUrlModel
}
