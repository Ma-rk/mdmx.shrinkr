package com.mdmx.shrinkr.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ShortLink")
class ShortLinkModel(
  @Column(nullable = false, updatable = false)
  val originalLink: String
) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  val linkSeq: Long? = null

  @Column(nullable = false, updatable = false)
  val insertedAt: LocalDateTime = LocalDateTime.now()

  @Column(length = 7, nullable = true, insertable = false, unique = true)
  var shortLink: String? = null

  @Column(length = 7, nullable = true, insertable = false, unique = true)
  var paddedShortLink: String? = null

  @Column(nullable = true, insertable = false)
  var creatorEmailAddr: String? = null

  @Column(nullable = true, insertable = false, unique = true)
  var deactivationCode: String? = null

  @Column(nullable = true, insertable = false)
  var deactivatedAt: LocalDateTime? = null
}
