package com.iyyxx.springjpa;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableKnife4j
@EnableJpaAuditing
@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

}
