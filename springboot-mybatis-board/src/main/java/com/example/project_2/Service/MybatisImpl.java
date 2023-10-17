package com.example.project_2.Service;

import com.example.project_2.DAO.Answer;
import com.example.project_2.DAO.PagingDAO;
import com.example.project_2.DAO.Question;
import com.example.project_2.Repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MybatisImpl implements MybatisService {
    private final BoardMapper boardMapper;


    @Override
    public List<Question> getList() {
        return boardMapper.getList();
    }

    @Override
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.boardMapper.getQuestion(id);
        return question.get();
    }

    @Override
    public int addAnswer(Answer answer) {
        return boardMapper.addAnswer(answer);
    }

    @Override
    public List<Answer> getAnswer(Integer id) {
        return boardMapper.getAnswer(id);
    }

    @Override
    public int addQuestion(Question question) {
        return boardMapper.addQuestion(question);
    }

    @Override
    public List<Question> authorGetList(PagingDAO pagingDAO) {
        return boardMapper.authorGetList(pagingDAO);
    }

    @Override
    public int authorGetTotal(PagingDAO pagingDAO) {
        return boardMapper.authorGetTotal(pagingDAO);
    }

}
