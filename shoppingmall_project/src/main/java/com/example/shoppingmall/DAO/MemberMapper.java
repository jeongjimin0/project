package com.example.shoppingmall.DAO;


import com.example.shoppingmall.VO.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int addQuestion(MemberVO memberVO);
}
