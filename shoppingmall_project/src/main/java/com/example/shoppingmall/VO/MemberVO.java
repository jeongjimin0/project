package com.example.shoppingmall.VO;

import lombok.Data;

@Data
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberMail;
    private String memberAddr1;
    private String memberAddr2;
    private String memberAddr3;
    private int adminCk;
    private int regDate;
    private int money;
    private int point;
}
