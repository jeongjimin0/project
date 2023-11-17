package com.example.shoppingmall.DAO;


import com.example.shoppingmall.VO.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    int addQuestion(MemberVO memberVO);
    int idCheck(String memberId);
    public MemberVO memberLogin(MemberVO memberVO);

}
