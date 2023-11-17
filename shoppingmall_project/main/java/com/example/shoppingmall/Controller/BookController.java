package com.example.shoppingmall.Controller;

import com.example.shoppingmall.VO.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;
import java.util.List;

@Controller
public class BookController {

    MemberVO result2;
    @RequestMapping("/main")
    public String mainPageGet(Model model) {
        System.out.println("result : "  + result2);
        model.addAttribute("member", result2);
        return "main";
    }

    public void setRes(MemberVO st){
        this.result2 = st;

    }
}
