package com.js.config.mvcconfig;

import com.js.config.handlerinterceptor.MyHandlerIntercepter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author jiangshuang
 * @Description 拦截未登录的操作
 **/
@Configuration
@Slf4j
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.info("添加视图解析器");
    }

    @Autowired
    private MyHandlerIntercepter myHandlerIntercepter;

    /**
     * 不拦截路径
     **/
    private String[] excludePathPatterns = {"/static/**", "/public", "/userLogin/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerIntercepter).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
    }
}
