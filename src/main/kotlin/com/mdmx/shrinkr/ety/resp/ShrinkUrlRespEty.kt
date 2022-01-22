package com.mdmx.shrinkr.ety.resp

import org.springframework.http.HttpStatus

/**
 * url 단축 리스폰스 바디를 담기 위한 entity
 */
class ShrinkUrlRespEty(
  val status: HttpStatus,
  val code: String,
  val message: String,
  val data: Map<String, Any?>
) {
}