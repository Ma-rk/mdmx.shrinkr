package com.mdmx.shrinkr.repository

import com.mdmx.shrinkr.model.ShortUrlModel
import org.springframework.data.repository.CrudRepository

interface ShortUrlRepository : CrudRepository<ShortUrlModel, Long> {
}
