package com.example.mybatis.controller;

import com.example.mybatis.dao.Answer;
import com.example.mybatis.mapper.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final Mappers mappers;

    @PostMapping("/answer/create/{id}")
    public String answerCreate(Model model, @PathVariable("id") String id, @RequestParam(value = "CONTENT", required = false) String content, Answer answer) {
//        answer.setID("10");
        answer.setQUESTION_ID(id);
        mappers.addAnswer(answer);
        return String.format("redirect:/question/detail/%s", id);
    }
}
