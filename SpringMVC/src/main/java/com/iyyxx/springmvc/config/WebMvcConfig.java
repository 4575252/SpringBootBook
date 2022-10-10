package com.iyyxx.springmvc.config;

import com.iyyxx.springmvc.handler.LogInterceptor;
import com.iyyxx.springmvc.handler.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @className: WebMvcConfig
 * @description: Knife4jWebMvcConfig
 * @author: eric 4575252@gmail.com
 * @date: 2022-10-07 12:16:23
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {
    @Autowired
    private LogInterceptor logInterceptor;
    @Autowired
    private TimeInterceptor timeInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(timeInterceptor);
//        registry.addInterceptor(logInterceptor);
    }

    /*** 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        // 文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iyyxx.springmvc.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringMVC测试工程")
                .version("1.0")
                .description("SpringMVC测试工程接口文档")
                .termsOfServiceUrl("http://localhost:8080/")
                .contact(new Contact("eric","b","4575252@gmail.com"))
                .build();
    }

}
