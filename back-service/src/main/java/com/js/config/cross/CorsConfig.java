package com.js.config.cross;

/**
 * @Author: 姜爽
 * @Description: 配置系统跨域
 * @Date: 2020/5/19 13:04
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        // 允许任何域名
        corsConfiguration.addAllowedHeader("*");
        // 允许任何头
        corsConfiguration.addAllowedMethod("*");
        // 允许任何方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        // 注册
        return new CorsFilter(source);
    }
}
