package com.example.mybatis.service;

import com.example.mybatis.dao.Answer;
import com.example.mybatis.dao.Question;

import java.util.List;

public interface MybatisService {

    List<Question> getList();

    Question findById(String key);
    List<Answer> getAnswer(String id);
    Question getQuestion(String id);

}
