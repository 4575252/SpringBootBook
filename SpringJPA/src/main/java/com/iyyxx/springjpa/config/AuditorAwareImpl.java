package com.iyyxx.springjpa.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @className: AuditorAwareImpl
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/11/0011 22:38:32
 **/
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // 添加一个随机数
        return Optional.of("管理员" + (int) (Math.random() * 10));
    }
}
