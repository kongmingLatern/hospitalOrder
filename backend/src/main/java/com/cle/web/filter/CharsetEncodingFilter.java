package com.cle.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * @Discription 字符编码过滤器
 **/
@WebFilter("*")
public class CharsetEncodingFilter implements Filter {

    private static String encoding; // 定义变量接收初始化的值

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置字符编码链锁
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);

    }

    // 初始化
    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = "utf-8";
    }

}