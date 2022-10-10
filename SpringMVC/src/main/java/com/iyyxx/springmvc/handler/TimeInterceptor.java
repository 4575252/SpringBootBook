package com.iyyxx.springmvc.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @className: TimeInterceptor
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/10/0010 17:03:27
 **/
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private final ThreadLocal<LocalTime> threadLocalStart = new ThreadLocal<>();
    private final ThreadLocal<LocalTime> threadLocalEnd = new ThreadLocal<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    // 记录开始时间
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LocalTime startTime = LocalTime.now();
        threadLocalStart.set(startTime);
        log.info("开始时间：{}", startTime.format(formatter));
        return true;
    }

    // 记录结束时间
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocalTime endTime = LocalTime.now();
        threadLocalEnd.set(endTime);
        log.info("结束时间：{}", endTime.format(formatter));
    }

    // 计算接口执行时间
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalTime startTime = threadLocalStart.get();
        LocalTime endTime = threadLocalEnd.get();
        log.info("接口执行时间：{} 毫秒", Duration.between(startTime, endTime).getNano() / 1000000);
    }
}