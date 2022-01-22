package com.mdmx.shrinkr.ety.req

import io.swagger.annotations.ApiModelProperty

/**
 * url 단축 리퀘스트 바디를 담기 위한 entity
 */
class ShrinkUrlReqEty(
  @ApiModelProperty(required = true, notes = "단축 대상 url")
  val urlToShrink: String,

  @ApiModelProperty(required = false, notes = "단축 url 을 삭제할 수 있는 링크 요청 여부")
  val deletionLinkChecked: Boolean,

  @ApiModelProperty(required = false, notes = "단축 url 삭제를 위한 링크를 받을 email 주소")
  val emailAddr: String
)
