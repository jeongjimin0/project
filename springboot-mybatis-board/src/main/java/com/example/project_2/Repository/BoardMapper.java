package com.example.project_2.Repository;

import com.example.project_2.DAO.Answer;
import com.example.project_2.DAO.PagingDAO;
import com.example.project_2.DAO.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    List<Question> getList();
    Optional<Question> getQuestion(Integer id);
    int addAnswer(Answer answer);
    List<Answer> getAnswer(Integer id);
    int addQuestion(Question question);
    int answerCnt(Integer id);

    public List<Question> authorGetList(PagingDAO pagingDAO);
    public int authorGetTotal(PagingDAO pagingDAO);

}
