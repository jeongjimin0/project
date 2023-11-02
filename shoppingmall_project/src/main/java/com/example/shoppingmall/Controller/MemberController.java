package com.example.shoppingmall.Controller;

import com.example.shoppingmall.Service.MemberService;
import com.example.shoppingmall.VO.MemberVO;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String loginGet() {
        return "login";
    }

    @RequestMapping("/member/join")
    public String joinGet() {
        return "join";
    }

    @PostMapping("/member/join")
    public String joinPost(MemberVO memberVO) {
        memberService.addQuestion(memberVO);
        return "main";
    }

    @PostMapping("/member/memberIdChk")
    @ResponseBody
    public String memberIdChkPOST(String memberId) throws Exception {
        int result = memberService.idCheck(memberId);
        if (result != 0) {
            return "fail";    // 중복 아이디가 존재
        } else {
            return "success";    // 중복 아이디 x
        } // memberIdChkPOST() 종료

    }

    @GetMapping("/mailCheck")
    @ResponseBody
    public void mailCheckGET(String email) throws Exception{

    }
}
