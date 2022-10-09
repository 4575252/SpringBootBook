# 关于本项目
[![Language](https://img.shields.io/badge/Language-Java_8_121-007396?color=orange&logo=java)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Framework-Spring_Boot_2.7.4-6DB33F?logo=spring)](https://github.com/4575252/SpringBootBook)
[![Lombok](https://img.shields.io/badge/Lombok-Spring_Boot_1.18.20-6DB33F?logo=lombok)](https://github.com/4575252/SpringBootBook)
[![Swagger2](https://img.shields.io/badge/Swagger2-Knife4j_3.0.2-6DB33F?logo=swagger)](https://github.com/4575252/SpringBootBook)

HelloWorld是很简单使用，但正式商用，至少应该集成这些插件，简化代码、提高效率：
- lombok，自动实现log、get、set、构造、toString、equals、hashCode方法
- knif4j，swagger2的增强框架，增加了接口测试功能

## lombok集成
操作过程
- pom文件修改
- 类注解@Slf4j可用log，实体注解@Data实现get、set等方法

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
</dependency>
```

```java
@Slf4j
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        // 这里推荐使用占位符{}，而不是采用+，可以避免出错
        log.info("通配符测试.{}.{}.{}", "A", "B", "C");
        return "Hello Spring Boot";
    }
}

```


## IDEA使用技巧
- 用一个项目存放多个工程，统一维护管理，就是本项目用到的技术啦
- 调整字体、皮肤、显示行号、方法分隔符、自定义工具栏、rainbow括号插件

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>3.0.2</version>
</dependency>
```

配置类信息
```java
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {

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
```

使用样例
```java
@Api("Hello控制器")
@Slf4j
@RestController
public class HelloController {

    @ApiOperation(value = "无注解方式", notes = "多个参数，多种的查询参数类型")
    @GetMapping("/hello")
    public String hello() {
        log.info("通配符测试.{}.{}.{}", "A", "B", "C");
        return "Hello Spring Boot";
    }

    @ApiOperation("@RequestParam方式")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "name", value = "用户名称", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", required = false)})
    @GetMapping("/requestParam")
    public User requestParam(String name, int age) {
        User u = new User(name, ++age);
        return u;
    }

    @ApiOperation("@PathVariable方式")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "用户名称", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", required = false)})
    @GetMapping("/pathVariable/{name}/{age}")
    public User pathVariable(@PathVariable String name, @PathVariable int age) {
        User u = new User(name, --age);
        return u;
    }

    @ApiOperation("@RequestBody方式")
    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户实体，参数用", required = false)})
    @PostMapping("/requestBody")
    public User requestBody(@RequestBody User user) {
        user.setAge(user.getAge() - 2);
        return user;
    }
}

```
swagger的测试可以访问, http://localhost:8081/doc.html
![](http://image.iyyxx.com/i/2022/10/09/6342706663c11.png)

## IDEA HttpClient 测试接口
HttpClient是IDEA默认集成的插件，如果没有可以手工下载安装。
使用的话在controller上点击相应的方法左侧进行测试！语法可以参考顶部的提示。
```http request
###
GET http://localhost:8081/hello

<> 2022-10-09T144803.200.txt
<> 2022-10-09T144659.200.txt

###
GET http://localhost:8081/requestParam?name=abc&age=55

<> 2022-10-09T144906.200.json

###
GET http://localhost:8081/pathVariable/eri/55

<> 2022-10-09T145024.200.json

###
POST http://localhost:8081/requestBody
Content-Type: application/json

{
  "age": 999,
  "name": "content"
}

<> 2022-10-09T145215.200.json
<> 2022-10-09T144711.400.json
```