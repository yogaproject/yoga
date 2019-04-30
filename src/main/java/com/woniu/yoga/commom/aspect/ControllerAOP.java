package com.woniu.yoga.commom.aspect;

import com.woniu.yoga.commom.exception.YogaException;
import com.woniu.yoga.commom.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description controller统一异常处理及打印日志
 * @Author guochxi
 * @Date 2019/4/30 10:05
 * @Version 1.0
 */

@Aspect
@Component
@Slf4j
public class ControllerAOP {

    @Pointcut("execution(public * com.woniu.yoga.*.controller.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        //类,方法
        log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName());

        //参数
        log.info("args={}"+joinPoint.getArgs());
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint){
        Result result = null;
        try{
            return proceedingJoinPoint.proceed();
        }catch (Throwable e){
            return new Result(500,"系统异常");
        }
    }
}
