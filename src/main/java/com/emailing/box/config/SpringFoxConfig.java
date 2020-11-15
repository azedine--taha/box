package com.emailing.box.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("users")
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.emailing.box.ressources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo("Book API", "Book Service API"));
    }

    /**
     * Creates an object containing API information including author name,
     * email, version, license, etc.
     *
     * @param title       API title
     * @param description API description
     * @return API information
     */
    private ApiInfo apiInfo(String title, String description) {
        Contact contact = new Contact("Azedine lamaouaj", "",
                "azedinetaha@gmail.com");
        return new ApiInfo(title, description, "1.0.0",
                "terms of service url",
                contact, "license", "license url",
                new ArrayList<>());
    }
}
