package com.example.shoppingmall.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /*
        이전 작업 중 세션이 완전히 제거되지 않아 로그인을 위해 새로운 세션을 저장할 때 발생할 수 있는 에러를 방지하기 위한 것
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("===================");
        System.out.println("LoginInterceptor preHandle 작동");
        HttpSession session = request.getSession();
        session.invalidate();

        return true;
    }
}
