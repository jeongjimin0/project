package com.example.project_2.DAO;

import lombok.Data;

@Data
public class Question {
    int ID;
    String SUBJECT;
    String CONTENT;
    String CREATEDATE;
    Answer answer;
}
