package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){

        // 初始化一个cors配置类对象
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许跨域访问的域名。*-所有域名。不要写*：安全性问题 不能携带cookie
        configuration.addAllowedOrigin("http://manager.gmall.com");
        configuration.addAllowedOrigin("http://www.gmall.com");
        configuration.addAllowedOrigin("http://gmall.com");
        configuration.addAllowedOrigin("http://localhost:1000");
        configuration.addAllowedOrigin("http://api.gmall.com");
        // 允许那些请求方式跨域访问。* get post delete put
        configuration.addAllowedMethod("*");
        // 允许携带的头信息
        configuration.addAllowedHeader("*");
        // 允许携带cookie
        configuration.setAllowCredentials(true);

        // 初始化cors配置源
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        // 注册cors配置
        configurationSource.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(configurationSource);
    }
}
