package com.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhou
 * @date 2020/6/27 2:02
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
     //apiInfo指定测试文档基本信息，这部分将在页面展示
     .apiInfo(apiInfo())
     .select()
     //apis() 控制哪些接口暴露给swagger，
     // RequestHandlerSelectors.any() 所有都暴露
     // RequestHandlerSelectors.basePackage("com.info.*")  指定包位置
     .apis(RequestHandlerSelectors.any())
     .paths(PathSelectors.any())
     .build();
  }

  //基本信息，页面展示
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
     .title("文件上传接口")
     .description("文件上传接口")
     //联系人实体类
     .contact(
      new Contact("王碧圣", "www.baidu.com", "123456789@qq.com")
     )
     //版本号
     .version("1.0.0-SNAPSHOT")
     .build();
  }
}
