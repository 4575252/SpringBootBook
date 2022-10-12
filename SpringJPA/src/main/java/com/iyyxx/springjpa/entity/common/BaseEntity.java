package com.iyyxx.springjpa.entity.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @className: BaseEntity
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/12/0012 9:58:57
 **/
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