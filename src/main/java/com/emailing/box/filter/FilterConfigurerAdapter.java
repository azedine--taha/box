package com.emailing.box.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class FilterConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthentificationFilter myFilter;

    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        FilterRegistrationBean regBean = new FilterRegistrationBean();
        regBean.setFilter(myFilter);
        regBean.setOrder(1);
        regBean.addUrlPatterns("/token");

        return regBean;
    }
}