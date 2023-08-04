package com.example.toy.service;

import com.example.toy.mapper.Mappers;
import com.example.toy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private Mappers userMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Employee> getUserList() {
        return userMapper.getUserList();
    }

    public Employee getUserById(String id) {
        return userMapper.getUserById(id);
    }

    public Employee getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public void signup(Employee userVo) { // 회원 가입
        if (!userVo.getEMPLOYEE_NO().equals("")) {
            // password는 암호화해서 DB에 저장
            userVo.setEMPLOYEE_PW(passwordEncoder.encode(userVo.getEMPLOYEE_PW()));
            userMapper.insertUser(userVo);
        }
    }

    public void edit(Employee userVo) { // 회원 정보 수정
        // password는 암호화해서 DB에 저장
        userVo.setEMPLOYEE_PW(passwordEncoder.encode(userVo.getEMPLOYEE_PW()));
        userMapper.updateUser(userVo);
    }

    public void withdraw(Long id) { // 회원 탈퇴
        userMapper.deleteUser(id);
    }

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}