package com.example.shoppingmall.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @RequestMapping("/main")
    public String mainPageGet() {
        return "main";
    }
}
