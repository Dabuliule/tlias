package com.tlias.filter;

import com.alibaba.fastjson.JSONObject;
import com.tlias.pojo.Result;
import com.tlias.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("requestURI: {}", requestURI);

        if (requestURI.contains("/login")) {
            log.info("登录操作, 放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String jwt = request.getHeader("token");

        if (!StringUtils.hasText(jwt)) {
            log.info("请求头token为空, 返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }

        log.info("令牌合法, 放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
