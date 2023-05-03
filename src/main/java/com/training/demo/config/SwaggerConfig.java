package com.training.demo.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:8088/training/swagger-ui/index.html
@Configuration
@EnableSwagger2
public class SwaggerConfig {  

   private static final String splitor = ";"; 

   public Docket createDocket(String groupName, ApiInfo apiInfo, String basePackage){
      return new Docket(DocumentationType.SWAGGER_2)
             .groupName(groupName)
             .apiInfo(apiInfo)
             .select()
             .apis(basePackage(basePackage))
             .paths(PathSelectors.any())
             .build();
   }
   
   @Bean
   public Docket createTrainingSystemDocket() {
      return createDocket("系統管理", trainingSystemApiInfo(), "com.training.demo.System.controller");
   }

   private ApiInfo trainingSystemApiInfo() {
      return new ApiInfoBuilder()
             .title("System API")
             .description("System API 文件")
             .version("1.0")
             .build();
   } 

   @Bean
   public Docket createTrainingFrontendDocket() {
      return createDocket("前台購物", trainingFrontendApiInfo(), "com.training.demo.Frontend.controller");
   }

   private ApiInfo trainingFrontendApiInfo() {
      return new ApiInfoBuilder()
             .title("Frontend API")
             .description("Frontend API 文件")
             .version("1.0")
             .build();
   }  
   
   @Bean
   public Docket createTrainingBackendDocket() {
      return createDocket("後台購物", trainingBackendApiInfo(), "com.training.demo.Backend.controller");
   }

   private ApiInfo trainingBackendApiInfo() {
      return new ApiInfoBuilder()
             .title("Backend API")
             .description("Backend API 文件")
             .version("1.0")
             .build();
   }  

   public static Predicate<RequestHandler> basePackage(final String basePackage) {
      return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
   }

   private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
      return input -> {
          for (String strPackage : basePackage.split(splitor)) {
             boolean isMatch = input.getPackage().getName().startsWith(strPackage);
             if (isMatch) {
                return true;
             }
          }
          return false;
      };
   }  

   private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
      return Optional.fromNullable(input.declaringClass());
   }  

} 