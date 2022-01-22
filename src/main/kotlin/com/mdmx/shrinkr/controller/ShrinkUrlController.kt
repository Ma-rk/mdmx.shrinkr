package com.mdmx.shrinkr.controller

import com.mdmx.shrinkr.ety.req.ShrinkUrlReqEty
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import mu.KotlinLogging
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@Api(description = "URL 단축 관련 API 모음")
@RestController
@RequestMapping("/api/v1/mdmx/shrinkr")
class ShrinkUrlController {
  private val lg = KotlinLogging.logger {}

  @ApiOperation(value = "URL 단축", notes = "URL 을 입력받아 단축된 URL 을 반환")
  @RequestMapping(method = [RequestMethod.POST], value = ["/shrinkUrl"], produces = ["application/json;charset=UTF-8"])
  fun shrinkUrl(@RequestBody reqEty: ShrinkUrlReqEty): String {
    val reqString = " ${reqEty.urlToShrink} ${reqEty.deletionLinkChecked} ${reqEty.emailAddr}"
    lg.info(reqString)
    return reqString
  }
}
