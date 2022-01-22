package com.mdmx.shrinkr.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Bean
  fun api(): Docket {
    val apiInfo = ApiInfoBuilder()
      .title("MDMX controller spec")
      .description("MDXD controller list")
      .version("1.0")
      .contact(
        Contact(
          "Kim Mark",
          "https://github.com/Ma-rk/",
          "kim.mark.dev@gmail.com"
        )
      )
      .build()

    return Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.mdmx.shrinkr.controller"))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo);
  }
}