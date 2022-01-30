package com.mdmx.shrinkr.ety.req

import io.swagger.annotations.ApiModelProperty

/**
 * Link 단축 리퀘스트 바디를 담기 위한 entity
 */
data class ShrinkLinkReqEty(
  @ApiModelProperty(required = true, notes = "단축 대상 Link", example = "https://github.com/Ma-rk/")
  val linkToShrink: String,

  @ApiModelProperty(required = false, notes = "단축 Link 를 삭제할 수 있는 링크 요청 여부", example = true.toString())
  val deletionLinkChecked: Boolean,

  @ApiModelProperty(required = false, notes = "단축 Link 삭제를 위한 링크를 받을 email 주소", example = "kim.mark.dev@gmail.com")
  val emailAddr: String?
)
