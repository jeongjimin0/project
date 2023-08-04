package com.example.mybatis.controller;

import com.example.mybatis.dao.Answer;
import com.example.mybatis.dao.Question;
import com.example.mybatis.mapper.Mappers;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final Mappers mappers;
    @RequestMapping("/")
    public String questionList(Model model) {
        List<Question> questionList = mappers.getList();
        model.addAttribute("question", questionList);
        return "question_list";
    }

    @RequestMapping("/question/detail/{id}")
    public String questionDetail(@PathVariable("id") String id, Model model) {
        Optional<Question> ex = mappers.findById(id);
        List<Answer> answer = mappers.getAnswer(id);
        Question questionList = mappers.getQuestion(id);
        model.addAttribute("answer", answer);
        model.addAttribute("question", questionList);
        return "question_detail";
    }

    @RequestMapping("/question/create")
    public String questionCreate() {
        return "question_create";
    }
}
