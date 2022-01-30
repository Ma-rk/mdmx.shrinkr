package com.mdmx.shrinkr.ety.resp

import org.springframework.http.HttpStatus

/**
 * Link 단축 리스폰스 바디를 담기 위한 entity
 */
data class ShrinkLinkRespEty(
  val status: HttpStatus,
  val statusCode: Int,
  val message: String,
  val data: Map<String, Any?>
) {
}
