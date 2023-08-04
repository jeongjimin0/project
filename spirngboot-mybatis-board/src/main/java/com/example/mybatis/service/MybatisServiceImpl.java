package com.example.mybatis.service;

import com.example.mybatis.dao.Answer;
import com.example.mybatis.dao.Question;
import com.example.mybatis.mapper.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MybatisServiceImpl implements MybatisService {

    private final Mappers mappers;

    @Override
    public List<Question> getList() {
        return mappers.getList();
    }

    @Override
    public Question findById(String key) {
        Optional<Question> question = this.mappers.findById(key);
        return question.get();
    }

    @Override
    public List<Answer> getAnswer(String id) {
        return mappers.getAnswer(id);
    }

    @Override
    public Question getQuestion(String id) {
        return mappers.getQuestion(id);
    }

}
