package com.red.moon.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author lihui
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static Logger logger = LoggerFactory.getLogger(com.red.moon.config.ServiceLogAspect.class);

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("execution(public * com.red.moon.controller..*.*(..))")
    public void logPointCut() {
    }

    //@Around：环绕通知
    @Around("logPointCut()")
    public void saveControllerLog(ProceedingJoinPoint proceedingJoinPoint) {


        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL :{} ", request.getRequestURL().toString());
        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        logger.info("参数 : {}", params);
        logger.info("请求者IP :{}", request.getRemoteAddr());

        //开始调用时间
        // 计时并调用目标函数
        long start = System.currentTimeMillis();
        Long time = System.currentTimeMillis() - start;
        logger.info("请求时间: {} ,花费时间:{}", params, time);
        try {
            Object result = proceedingJoinPoint.proceed();
            logger.info("请求成功,结果 :{} ", result.toString());
            System.out.println("环绕通知结束。。。。。");
        } catch (Throwable throwable) {
            logger.info("请求失败");
            logger.error(throwable.getMessage());
            throwable.printStackTrace();
        }

    }
}

