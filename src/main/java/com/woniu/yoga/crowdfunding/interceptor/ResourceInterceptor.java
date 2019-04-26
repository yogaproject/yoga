package com.woniu.yoga.crowdfunding.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project yoga
 * @Description 资源放行
 * @Author HanFeng
 * @Create in 2019/4/26 10:54
 */
@Component
public class ResourceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if ( uri.endsWith(".js") || uri.endsWith(".css") ||
                uri.endsWith(".png") || uri.endsWith(".jpg") ||
                    uri.endsWith(".html") || uri.endsWith(".jpeg") ||
                        uri.endsWith(".gif")){
            return true;
        }
        return false;
    }
}
