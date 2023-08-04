package com.example.mybatis.dao;

import lombok.Data;

@Data
public class Question {
    String ID;
    String SUBJECT;
    String CONTENT;
    String CREATE_DATE;
    Answer answer;
}
