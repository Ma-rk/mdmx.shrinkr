package com.mdmx.shrinkr.controller

import com.mdmx.shrinkr.service.FindUrlService
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
  private val findUrlService: FindUrlService
) {
  @ApiOperation(value = "원래 주소로 redirect", notes = "입력받은 단축주소의 원래주소를 찾아 redirect 함")
  @RequestMapping(method = [RequestMethod.GET], value = ["/{shortUrl}"], produces = ["application/json;charset=UTF-8"])
  fun redirectToOriginalUrl(@PathVariable shortUrl: String): String {
    return "redirect:${findUrlService.findOriginalUrl(shortUrl)}"
  }
}