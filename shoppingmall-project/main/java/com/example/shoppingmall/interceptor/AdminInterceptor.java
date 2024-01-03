package com.example.shoppingmall.interceptor;

import com.example.shoppingmall.VO.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Bean
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        MemberVO lvo = (MemberVO)session.getAttribute("member");
        if(lvo == null || lvo.getAdminCk() == 0) {    // 관리자 계정 아닌 경우

            response.sendRedirect("/main");    // 메인페이지로 리다이렉트

            return false;

        }

        return true;    // 관리자 계정 로그인 경우(lvo != null && lvo.getAdminCk() == 1)
    }
}

// 관리자 계정이 아닐 경우 /admin/main 을 하더라도 main 페이지로 리다이렉트
