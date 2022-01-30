package com.mdmx.shrinkr.controller

import com.mdmx.shrinkr.service.FindLinkService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Api(description = "원래주소로 Redirect 관련 API 모음")
@Controller
@RequestMapping("/re")
class RedirectController(
  private val findLinkService: FindLinkService
) {
  @ApiOperation(value = "원래 주소로 redirect", notes = "입력받은 단축주소의 원래주소를 찾아 redirect 함")
  @RequestMapping(method = [RequestMethod.GET], value = ["/{shortLink}"], produces = ["application/json;charset=UTF-8"])
  fun redirectToOriginalLink(@PathVariable shortLink: String): String {
    return "redirect:${findLinkService.findOriginalLink(shortLink)}"
  }
}