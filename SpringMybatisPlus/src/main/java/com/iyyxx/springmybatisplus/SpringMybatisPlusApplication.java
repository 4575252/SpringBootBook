package com.iyyxx.springmybatisplus;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableKnife4j
@MapperScan("com.iyyxx.springmybatisplus.mapper")
@SpringBootApplication
public class SpringMybatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisPlusApplication.class, args);
	}

}
