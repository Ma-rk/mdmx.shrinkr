package com.mdmx.shrinkr.controller

import com.mdmx.shrinkr.ety.req.ShrinkLinkReqEty
import com.mdmx.shrinkr.ety.resp.ShrinkLinkRespEty
import com.mdmx.shrinkr.service.ShrinkLinkService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import mu.KotlinLogging
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@Api(description = "Link 단축 관련 API 모음")
@RestController
@RequestMapping("/api/v1/mdmx/shrinkr")
class ShrinkLinkController(
  val shrinkLinkService: ShrinkLinkService
) {
  private val lg = KotlinLogging.logger {}

  @ApiOperation(value = "Link 단축", notes = "Link 를 입력받아 단축된 Link 를 반환")
  @RequestMapping(method = [RequestMethod.POST], value = ["/shrinkLink"], produces = ["application/json;charset=UTF-8"])
  fun shrinkLink(@RequestBody reqEty: ShrinkLinkReqEty): ShrinkLinkRespEty {
    return shrinkLinkService.shrinkLinkViaHashids(reqEty)
  }
}
