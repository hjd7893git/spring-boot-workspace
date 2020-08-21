package swiggerdoc.config;


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

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean(name = "奥茨用户")
    public Docket createRestCApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//这是注意的代码
                .apis(RequestHandlerSelectors.basePackage("swiggerdoc.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("hjd用户");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("hjd用户接口文档")
                .description("hjd用户相关接口的文档")
                .contact(new Contact("yuex", "", "403215062@qq.com"))
                //.termsOfServiceUrl("http://www.xxx.com")
                .version("1.0")
                .build();
    }
}
