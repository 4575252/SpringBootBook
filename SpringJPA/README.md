# 关于本项目
[![Language](https://img.shields.io/badge/Language-Java_8_121-007396?color=silver&logo=java)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Framework-Spring_Boot_2.7.4-6DB33F?logo=spring)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Persistence-Spring_Data_JPA-orange?logo=Persistence)](https://github.com/4575252/SpringBootBook)
[![Lombok](https://img.shields.io/badge/Lombok-Spring_Boot_1.18.20-pink?logo=lombok)](https://github.com/4575252/SpringBootBook)
[![Swagger2](https://img.shields.io/badge/Swagger2-Knife4j_3.0.2-blue?logo=swagger)](https://github.com/4575252/SpringBootBook)

SpringJPA是持久化两个主要分支JPA的经典实现，本项目主要实验以下内容：
- 通过集成mysql，完成jpa的配置，实现表的自动创建
- 实现常规的CRUD
- 实现复杂功能，如批量保存、批量删除、复杂查询、分页查询、排序
- 实现增强功能，依托JPA的语法实现定制查询
- 实现JPQL、SQL的扩展用法，解决死角问题
- 实现审计接口，即公共字段填充，如创建人、创建时间、修改人、修改时间

## 实验一：集成mysql、jpa
- 常用集成lombok、knife4j（含WebMvcConfig）
- pom.xml
- application.yml
- 填写测试entity，启动看日志及数据库是否自动新增了表（ddl=update）

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

```yaml
spring:
  # 数据库配置
  datasource:
    url: jdbc:mysql://192.168.20.145:3306/JPA?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 123456
  # JPA 配置
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect

knife4j:
  enable: true

logging:
  level:
    org.hibernate.type.descriptor.sql.BasicBinder: trace
    root: info

# WEB 配置
server:
  port: 8080
  servlet:
    context-path: /springjpa
    session:
      timeout: 60
```

```java
@Data
@Entity
@Table(indexes = {@Index(name = "uk_email",columnList = "email",unique = true)})
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,columnDefinition = "varchar(20) comment '姓名'")
    private String name;

    @Transient
    private int age;

    @Column(nullable = false,length = 50)
    private String email;

    private LocalDate birthDay;

}
```

## 实验二： 实现常规的CRUD和批量保存、批量删除等常用功能
JPA的常规使用，只要一个接口继承JpaRepository即可实现，相应的controller、service不再赘述可以查看其他文档。

批量保存、删除等操作主要是基于JpaRepository的父类CrudRepository来实现！
```java
public interface UserRepository extends JpaRepository<User,Integer> {
}
```

## 实验三： 分页查询、排序
paRepository的父类PagingAndSortingRepository都封装好了，可以直接使用！
```java
public Page<User> findAll(String property,
    Sort.Direction direction,
    Integer page,
    Integer size) {
    Pageable pageable = PageRequest.of(page, size, direction, property);
    return userRepository.findAll(pageable);
}
```


## 实验四： JPA约定方法实践，如批量保存、批量删除、复杂查询、分页查询、排序
相关约定方法的参考手册可以看这里， https://blog.csdn.net/weixin_43094965/article/details/117203091

```java
public interface UserRepository extends JpaRepository<User,Integer> {
    //这里用约定方法实现基于name的前后模糊匹配
    List<User> findByNameContaining(String name);
}
```

## 实验五： 实现JPQL、SQL的扩展用法，解决死角问题
JPQL和sql的区别有这几点：
- jpql采用面向对象的驼峰命名法，包括属性也是一样；而sql的实体是纯小写，属性如果有两个单词用下划线隔开！
- 原生sql的注解@query中要设置nativeQuery = true， 方法参数也需要设置@Param注解！

```java
public interface UserRepository extends JpaRepository<User,Integer> {
    // 根据名称查询
    @Query("select u from User u where u.name = ?1")
    User fingByName(String name);

    // 根据名称模糊查询，左侧模糊
    @Query("select u from User u where u.name like %?1")
    List<User> findByName(String name);

    @Query(value = "select * from user where birth_day =:birthDay",nativeQuery = true)
    List<User> findByBirthDayNative(@Param("birthDay") String birthDay);
}
```

## 实验六： 实现审计接口，即公共字段填充，如创建人、创建时间、修改人、修改时间
审计接口是在实体中增加创建人、创建时间，修改人、修改时间，通过注解的约定让平台自动赋值，一般大范围使用就采用基类处理，具体如下：
- 实体类修改，自行填入审计字段或继承基类
- SpringBoot启动类开始@EnableJpaAuditing注解
- 实现AuditorAware接口，重写getCurrentAuditor方法，填入操作员姓名，实现修改、创建时的操作员写入，这里硬编码，一般采用SpringSecurity的用户取出

```java
// 修改1、SpringJpaApplication开启@EnableJpaAuditing
@EnableSwagger2
@EnableKnife4j
@EnableJpaAuditing  //关键
@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

}

// 修改2、公共基类
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @Column(updatable = false,length = 20)
    private String creator;

    @LastModifiedBy
    @Column(length = 20)
    private String modifier;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}

// 修改3、实体类继承
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {@Index(name = "uk_email",columnList = "email",unique = true)})
public class User extends BaseEntity {

    //…………
}

// 修改4、重写AuditorAware接口的getCurrentAuditor方法，实现操作员写入
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // 添加一个随机数
        return Optional.of("管理员" + (int) (Math.random() * 10));
    }
}
```