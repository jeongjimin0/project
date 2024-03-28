package com.example.toy.model;

import lombok.Data;

@Data
public class Employee {
    private String EMPLOYEE_NO;
    private String EMPLOYEE_PW;
    private String POST_POSITION;
    private String POSITION;
    private String DUTY;
    private String AUTH; //권한
    private String EM_NAME; //권한
    private String ORGANIZATION;
    private String REGIST_USER_ID;
    private String UPDATE_USER_ID;
    private String REGIST_DATE;
    private String UPDATE_DATE;

}
