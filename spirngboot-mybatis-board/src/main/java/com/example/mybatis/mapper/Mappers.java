package com.example.mybatis.mapper;

import com.example.mybatis.dao.Answer;
import com.example.mybatis.dao.Question;

import java.util.List;
import java.util.Optional;

public interface Mappers {
    List<Question> getList();
    Optional<Question> findById(String key);
    List<Answer> getAnswer(String id);
    Question getQuestion(String id);



}
