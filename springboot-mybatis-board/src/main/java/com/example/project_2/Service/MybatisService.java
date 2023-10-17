package com.example.project_2.Service;

import com.example.project_2.DAO.Answer;
import com.example.project_2.DAO.PagingDAO;
import com.example.project_2.DAO.Question;

import java.util.List;
import java.util.Optional;

public interface MybatisService {

    List<Question> getList();
    Question getQuestion(Integer id);
    int addAnswer(Answer answer);
    List<Answer> getAnswer(Integer id);
    int addQuestion(Question question);
    public List<Question> authorGetList(PagingDAO pagingDAO);
    public int authorGetTotal(PagingDAO pagingDAO);
}
