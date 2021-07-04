package com.db.tradestore.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class StoreAPIDocumentationConfig
{
   @Bean
   public Docket customImplementation()
   {
      return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.db.tradestore"))
            .build()
            .securitySchemes(basicScheme())
            .apiInfo(apiInfo());
   }

   private List<SecurityScheme> basicScheme()
   {
      List<SecurityScheme> schemeList = new ArrayList<>();
      schemeList.add(new BasicAuth("basicAuth"));
      return schemeList;
   }

   ApiInfo apiInfo()
   {
      return new ApiInfoBuilder()
            .title("Trade Store API")
            .description("API to create , update and get all trades")
            .license("")
            .licenseUrl("https://unlicense.org")
            .termsOfServiceUrl("")
            .version("1.0")
            .contact(new Contact("", "", ""))
            .build();
   }

}
