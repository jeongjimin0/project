package com.example.project_2.Controller;

import com.example.project_2.DAO.Answer;
import com.example.project_2.DAO.Question;
import com.example.project_2.Repository.BoardMapper;
import com.example.project_2.Service.MybatisImpl;
import com.example.project_2.Service.MybatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final MybatisService mybatisService;

    @RequestMapping("/list")
    public String list(Model model){
        List<Question> questionList = mybatisService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }


    @RequestMapping(value =  "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.mybatisService.getQuestion(id);
        List<Answer> answerList = mybatisService.getAnswer(id);
        model.addAttribute("question", question);
        model.addAttribute("Answer", answerList);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(){
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam String SUBJECT, @RequestParam String CONTENT, Question question) {
        mybatisService.addQuestion(question);
        return "redirect:/question/list";
    }

}
