//package com.amur.configuration;
//
//import com.amur.areas.interseptors.TitleInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//
//    private final TitleInterceptor titleInterceptor;
//
//    @Autowired
//    public WebMvcConfig(TitleInterceptor titleInterceptor) {
//        this.titleInterceptor = titleInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.titleInterceptor);
//    }
//}
