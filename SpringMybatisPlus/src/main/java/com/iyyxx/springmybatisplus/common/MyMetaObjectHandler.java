package com.iyyxx.springmybatisplus.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @className: MyMetaObjectHandler
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/12/0012 11:03:45
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "creator", this::getCurrentUser, String.class);
        this.strictInsertFill(metaObject, "modifier", this::getCurrentUser, String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "modifier", this::getCurrentUser, String.class);
    }


    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }

    // 模拟获取当前用户
    private String getCurrentUser() {
        //后续可调整为从ThreadLocal或SpringSecurity中取
        return "管理员" + (int) (Math.random() * 10);
    }
}