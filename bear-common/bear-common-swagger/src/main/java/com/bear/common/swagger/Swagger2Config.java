package com.bear.common.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class Swagger2Config {

    @Value("${swagger2.packages}")
    private String packages;
    @Value("${swagger2.title}")
    private String title;
    @Value("${swagger2.description}")
    private String description;
    @Value("${swagger2.url}")
    private String url;
    @Value("${swagger2.contact.name}")
    private String contactName;
    @Value("${swagger2.contact.url}")
    private String contactUrl;
    @Value("${swagger2.contact.email}")
    private String contactEmail;
    @Value("${swagger2.version}")
    private String version;
    @Value("${swagger2.enable}")
    private String enable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(Boolean.valueOf(enable))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(url).contact(new Contact(contactName,contactUrl,contactEmail))
                .version(version)
                .build();
    }
}
