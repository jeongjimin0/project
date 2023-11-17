package com.example.shoppingmall.Service;

import com.example.shoppingmall.VO.MemberVO;

public interface MemberService {
    int addQuestion(MemberVO memberVO);
    int idCheck(String memberId);
    public MemberVO memberLogin(MemberVO memberVO);
}
