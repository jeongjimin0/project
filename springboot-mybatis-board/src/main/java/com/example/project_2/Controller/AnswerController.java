package com.example.project_2.Controller;

import com.example.project_2.DAO.Answer;
import com.example.project_2.DAO.Question;
import com.example.project_2.Service.MybatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final MybatisService mybatisService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String CONTENT, Answer answer){
        answer.setQUESTION(id);
        Question question = this.mybatisService.getQuestion(id);
        mybatisService.addAnswer(answer);
        return String.format("redirect:/question/detail/%s", id);
    }
}
