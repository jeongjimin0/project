package com.example.shoppingmall.Service;


import com.example.shoppingmall.DAO.MemberMapper;
import com.example.shoppingmall.VO.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public int addQuestion(MemberVO memberVO) {
        return memberMapper.addQuestion(memberVO);
    }


}
