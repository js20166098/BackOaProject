package com.js.common.aspect;

import com.js.common.annotation.Log;
import com.js.common.util.IdUtils;
import com.js.common.util.RequestUtils;
import com.js.dto.system.SysLogDto;
import com.js.service.system.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: jiangshuang
 * @Description: 系统日志切面
 * @Date: 2020/5/3 14:37
 **/
@Aspect
@Component
@Slf4j
public class LogsAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.js.common.annotation.Log)")
    public void logPointCut() {
        log.info("进入切面");
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogDto sysLog = new SysLogDto();
        sysLog.setUuid(IdUtils.get32Uuid());
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 注解上的描述
            sysLog.setOperation(log.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        sysLog.setResponseTime(String.valueOf(time));
        // 获取request
        HttpServletRequest request = RequestUtils.getHttpServletRequest();
        String studentNumber = request.getHeader("studentNumber");
        String name = request.getHeader("name");
        String user = name + "+" + studentNumber;
        // 设置IP地址
        sysLog.setIp(RequestUtils.getIpAddr(request));
        sysLog.setOperUser(user);
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            sysLog.setRequestParams(Arrays.toString(args));
        } catch (Exception e) {
            sysLog.setRequestParams(null);
        }
        sysLogService.addSysLog(sysLog);
    }

}
