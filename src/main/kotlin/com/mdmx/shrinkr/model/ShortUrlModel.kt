package com.mdmx.shrinkr.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ShortUrl")
class ShortUrlModel(
  @Column(nullable = false, updatable = false)
  val originalUrl: String
) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  val urlSeq: Long? = null

  @Column(nullable = false, updatable = false)
  val insertedAt: LocalDateTime = LocalDateTime.now()

  @Column(length = 7, nullable = true, insertable = false, unique = true)
  var shortUrl: String? = null

  @Column(length = 7, nullable = true, insertable = false, unique = true)
  var paddedShortUrl: String? = null

  @Column(nullable = true, insertable = false)
  var creatorEmailAddr: String? = null

  @Column(nullable = true, insertable = false, unique = true)
  var deactivationCode: String? = null

  @Column(nullable = true, insertable = false)
  val deactivateAt: LocalDateTime? = null
}
