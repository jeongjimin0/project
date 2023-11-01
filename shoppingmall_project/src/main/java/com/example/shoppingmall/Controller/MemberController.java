package com.example.shoppingmall.Controller;

import com.example.shoppingmall.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String loginGet(){
        return "login";
    }

    @RequestMapping("/member/join")
    public String joinGet() {
        return "join";
    }

}
