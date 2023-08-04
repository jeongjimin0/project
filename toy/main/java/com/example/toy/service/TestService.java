package com.example.toy.service;


import com.example.toy.mapper.SearchDTO;
import com.example.toy.model.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
@Component
public interface TestService {


//    public int insertContent2(Connect connect);

//    public int insertContent(Business business);

    public int insertContent(Information information);
    public int insertContent2(Business business) throws Exception;
    public int insertContent3(Business2 business2);
    public int insertContent4(Business3 business3);

    public List<Business> getList();


    public List<Information> findAllPost(final Criteria cri);
    public List<Information> findAllPost2(final Criteria cri);
    public List<Information> findAllPost3(final Criteria cri);
    public List<Information> findAllPost4(final Criteria cri);
    Optional<Category> findAllPost10(Num num);
    Optional<Information> findAllPost11(String Key);


    Optional<Information> findById(String key);
    Optional<Information> findById_2(String key);
    Optional<Information> findById_3(String key);
    Optional<Information> findById_4(String key);
    Optional<Information> findById_5(String key);
    List<Business> findById_6(Business4 business4);

    public List<Information> getList(String img);


    int deleteById(String key);
    int deleteById2(String key);
    int deleteById3(String key);

    int UpdateById(String key);


    public List<Information> getImp(Business4 business4);

    List<Information> getTotal(String img);
    List<Business> totalGet();

    public List<Business> getList5(Criteria criteria);
    public Integer getTotal5();

    /* 작가 목록 */
    public List<Information> authorGetList(Criteria cri) throws Exception;

    /* 작가 총 수 */
    public int authorGetTotal(Criteria cri) throws Exception;

    /* 회원가입 */
    public int saveUser(Employee employee);

    List<Information> findAll12();

    List<Information> getDeleteList();
    Business getDeleteSelect(String img);


//    public PasswordEncoder passwordEncoder();

//    public List<Connect> boardSearch(String keyword);
}
