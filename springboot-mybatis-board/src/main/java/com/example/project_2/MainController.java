package com.example.project_2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainController {

    @RequestMapping("/ddd")
    @ResponseBody
    public String index() {
        return "테스트";
    }

}
