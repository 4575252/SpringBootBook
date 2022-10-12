package com.iyyxx.springjpa.entity;


import com.iyyxx.springjpa.entity.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {@Index(name = "uk_email",columnList = "email",unique = true)})
public class User extends BaseEntity {

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