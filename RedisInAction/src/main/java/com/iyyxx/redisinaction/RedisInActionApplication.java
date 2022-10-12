package com.iyyxx.redisinaction;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableKnife4j
@EnableSwagger2
@SpringBootApplication
public class RedisInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisInActionApplication.class, args);
    }

}
