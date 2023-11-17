package com.example.shoppingmall.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {


    @GetMapping("/test")
    public String testM(Model model) {
        model.addAttribute("model", "test");
        return "test";
    }
}
