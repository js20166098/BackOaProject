package com.js.config.handlerinterceptor;

import com.js.common.util.TokenUtil;
import com.js.config.OaSysConfig;
import com.js.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 姜爽
 * @Date 2019/12/3
 * @Description 拦截器设置 实现token拦截访问
 */
@Component
@Configuration
@Slf4j
public class MyHandlerIntercepter implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Autowired
    private OaSysConfig oaSysConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        log.info("执行preHandle方法-->01");
        Boolean flag = oaSysConfig.getInterceptorSwitch();
        if (Boolean.TRUE.equals(flag)) {
            boolean status = false;
            response.setCharacterEncoding(oaSysConfig.getDefaultEncoding());
            String token = request.getHeader("token");
            if (null != token) {
                Map<String, String> hashMap = new HashMap<>(8);
                try {
                    hashMap = TokenUtil.getTokenInfo(token);
                    String tokenTemp = redisService.getToken(hashMap.get("studentNumber"));
                    /** 验证token是否有效 **/
                    if (null != tokenTemp && token.equals(tokenTemp)) {
                        // 将信息封装到请求头
                        modifyHeaders(hashMap, request);
                        status = true;
                    }
                } catch (Exception e) {
                    log.info("Token异常", e);
                    return false;
                }
            }
            log.info("status的值为{}", status);
            return status;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        log.info("执行postHandle方法-->02");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        log.info("执行afterCompletion方法-->03");
    }

    /**
     * @Author: jiangshuang Description:token解析请求头
     **/
    private static void modifyHeaders(Map<String, String> hashmap, HttpServletRequest request) {
        if (hashmap == null || hashmap.isEmpty()) {
            return;
        }
        Class<? extends HttpServletRequest> requestClass = request.getClass();
        try {
            Field request1 = requestClass.getDeclaredField("request");
            request1.setAccessible(true);
            Object o = request1.get(request);
            Field coyoteRequest = o.getClass().getDeclaredField("coyoteRequest");
            coyoteRequest.setAccessible(true);
            Object o1 = coyoteRequest.get(o);
            Field headers = o1.getClass().getDeclaredField("headers");
            headers.setAccessible(true);
            MimeHeaders o2 = (MimeHeaders)headers.get(o1);
            for (Map.Entry<String, String> entry : hashmap.entrySet()) {
                o2.removeHeader(entry.getKey());
                o2.addValue(entry.getKey()).setString(entry.getValue());
            }
        } catch (Exception e) {
            log.info("请求头设置异常{}", e);
        }
    }
}
