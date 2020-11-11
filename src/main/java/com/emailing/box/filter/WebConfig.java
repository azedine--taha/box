package com.emailing.box.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import javax.servlet.Filter;

//@Configuration
public class WebConfig {



    @Bean
    public FilterRegistrationBean contextFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(AuthentificationFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("authentificationFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public Filter AuthentificationFilter (){
        return new AuthentificationFilter();
    }
}
