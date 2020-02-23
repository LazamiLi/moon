package com.red.moon.config;

import com.alibaba.fastjson.JSON;
import com.red.moon.bean.LogBean.SysLog;
import com.red.moon.service.ISysLogService;
import com.red.moon.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lihui
 * 系统日志：切面处理类
 * todo 缺少权限
 */
@Aspect
@Component
public class ServiceLogAspect {

    private static Logger  logger = LoggerFactory.getLogger(ServiceLogAspect.class);
    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("execution(public * com.red.moon.service.impl..*.*(..))")
    public void logPoinCut() {
    }
   
    @Around("logPoinCut()")
    public void saveSysLog(ProceedingJoinPoint proceedingJoinPoint) {
        //保存日志
        SysLog sysLog = new SysLog();
        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);
        sysLog.setDate(new Date());
        //获取用户名
        //获取用户ip地址
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        sysLog.setActionUrl(request.getRequestURL().toString());
        sysLog.setIp(request.getRemoteAddr());
        try {
             Object result = proceedingJoinPoint.proceed();
            if (result instanceof Result) {
                sysLog.setResult(result.toString());
            }
        } catch (Throwable throwable) {
            sysLog.setResult(throwable.getMessage());
            throwable.printStackTrace();
        }finally {
            try {
                sysLogService.insert(sysLog);
            }catch (Exception e){
                logger.error("日志插入失败:{}",e.getMessage());
                e.printStackTrace();
            }

        }
    }
}
