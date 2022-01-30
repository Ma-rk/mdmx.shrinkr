package com.mdmx.shrinkr.controller

import com.mdmx.shrinkr.service.DeactivationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import mu.KLogger
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Api(description = "단축된 주소 삭제 관련 API 모음")
@Controller
@RequestMapping("/de")
class DeactivationController(
  private val deactivationService: DeactivationService
) {
  private val lg: KLogger = KotlinLogging.logger {}

  @ApiOperation(value = "단축된 주소 비활성화", notes = "단축된 주소를 비활성화한다")
  @RequestMapping(method = [RequestMethod.GET], value = ["/{deactivationCode}"], produces = ["application/json;charset=UTF-8"])
  fun deactivateShortLink(@PathVariable deactivationCode: String): String {
    val deactivationResult = deactivationService.deactivateShortLink(deactivationCode)
    return if (deactivationResult) {
      lg.info { "deactivated succeed. redirecting to success page" }
      "redirect:/deactivationSucceeded.html"
    } else {

      lg.info { "deactivated failed. redirecting to fail page" }
      "redirect:/deactivationFailed.html"
    }
  }
}