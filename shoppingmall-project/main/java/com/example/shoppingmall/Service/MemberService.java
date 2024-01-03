package com.example.shoppingmall.Service;

import com.example.shoppingmall.VO.AuthorVO;
import com.example.shoppingmall.VO.MemberVO;

public interface MemberService {
    int addQuestion(MemberVO memberVO);
    int idCheck(String memberId);
    public MemberVO memberLogin(MemberVO memberVO);
    public void authorEnroll (AuthorVO author) throws Exception;
}
