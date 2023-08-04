package com.example.toy.service;

import com.example.toy.config.AES256;
import com.example.toy.mapper.Mappers;
import com.example.toy.mapper.SearchDTO;
import com.example.toy.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.Optional;



@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final Mappers mapper;
    private static SqlSessionFactory sqlMapper;


    @Override
    public int insertContent(Information information) {

        return mapper.insert(information);
    }

    @Override
    public int insertContent2(Business business) throws Exception {
        AES256 aes256 = new AES256();
        business.setRRN_NO(aes256.encrypt(business.getRRN_NO()));
        return mapper.insert2(business);
    }

    @Override
    public int insertContent3(Business2 business2) {

        return mapper.insert3(business2);
    }

    @Override
    public int insertContent4(Business3 business3) {

        return mapper.insert4(business3);

    }

    @Override
    public List<Business> getList() {
        return mapper.getList();
    }


    @Override
    public List<Information> findAllPost(Criteria cri) {

        return mapper.findAll(cri);
    }

    @Override
    public List<Information> findAllPost2(Criteria cri) {
        return mapper.findAll2(cri);
    }

    @Override
    public List<Information> findAllPost3(Criteria cri) {
        return mapper.findAll3(cri);
    }

    @Override
    public List<Information> findAllPost4(Criteria cri) {
        return mapper.findAll4(cri);
    }

    @Override
    public Optional<Category> findAllPost10(Num num) {
        return mapper.findAll10(num);
    }

    @Override
    public Optional<Information> findAllPost11(String Key) {
        return mapper.findAll11(Key);
    }


    @Override
    public Optional<Information> findById(String key) {
        return mapper.findById(key);
    }

    @Override
    public Optional<Information> findById_2(String key) {
        return mapper.findById_2(key);
    }

    @Override
    public Optional<Information> findById_3(String key) {
        return mapper.findById_3(key);
    }

    public Optional<Information> findById_4(String key) {
        return mapper.findById_4(key);
    }

    public Optional<Information> findById_5(String key) {
        return mapper.findById_5(key);
    }

    public List<Business> findById_6(Business4 business4) {
        return mapper.findById_6(business4);
    }

    @Override
    public List<Information> getList(String img) {
        return mapper.getList(img);
    }

    @Override
    public int deleteById(String key) {
        return mapper.deleteById(key);
    }
    @Override
    public int deleteById2(String key) {
        return mapper.deleteById2(key);
    }
    @Override
    public int deleteById3(String key) {
        return mapper.deleteById3(key);
    }

    @Override
    public int UpdateById(String key) {
        return mapper.UpdateById(key);
    }


    @Override
    public List<Information> getImp(Business4 business4) {

        return mapper.getImp(business4);
    }


    @Override
    public List<Information> getTotal(String img) {
        return mapper.getTotal(img);
    }


    @Override
    public List<Business> totalGet() {
        return mapper.totalGet();
    }

    @Override
    public List<Business> getList5(Criteria criteria) {
        return mapper.getList5(criteria);
    }

    @Override
    public Integer getTotal5() {
        return mapper.getTotal5();
    }

    @Override
    public List<Information> authorGetList(Criteria cri) throws Exception {
        return mapper.authorGetList(cri);
    }

    @Override
    public int authorGetTotal(Criteria cri) throws Exception {
        return mapper.authorGetTotal(cri);
    }


    @Override
    public List<Information> findAll12() {
        return mapper.findAll12();

    }

    @Override
    public List<Information> getDeleteList() {
        return mapper.getDeleteList();
    }

    @Override
    public Business getDeleteSelect(String img) {
        return mapper.getDeleteSelect(img);
    }


    @Override
    @Transactional
    public int saveUser(Employee employee) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setEMPLOYEE_PW(passwordEncoder.encode(employee.getEMPLOYEE_PW()));
        employee.setAUTH("USER");
        return mapper.saveUser(employee);
    }


//    @Override
//    public List<Business> find(Criteria criteria) {
//        log.info("getList........"+criteria);
//
//        return mapper.find(criteria);
//    }

}


