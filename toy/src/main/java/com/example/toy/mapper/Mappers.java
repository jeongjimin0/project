package com.example.toy.mapper;

import com.example.toy.model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper
public interface Mappers {

    public List<Business> getList();
    public List<Information> getList2();
    public List<Business3> getList3();

//    int insert(Business business);
    int insert(Information information);
    int insert3(Business2 business2);
    int insert4(Business3 business3);
    int insert2(Business business);

    List<Information> findAll(Criteria cri);
    List<Business> totalGet();
    List<Information> findAll2(Criteria cri);
    List<Information> findAll3(Criteria cri);
    List<Information> findAll4(Criteria cri);
    Optional<Category> findAll10(Num num);
    Optional<Information> findAll11(String Key);

    List<Information> findAll12();


    Optional<Information> findById(String key);
    Optional<Information> findById_2(String key);
    Optional<Information> findById_3(String key);
    Optional<Information> findById_4(String key);

    Optional<Information> findById_5(String key);
    List<Information> findById_7(Num num);

    List<Business> findById_6(Business4 business4);

    int deleteById(String key);
    int deleteById2(String key);
    int deleteById3(String key);

    int UpdateById(String key);



    List<Information> getImp(Business4 business4);
    List<Information> getTotal(String img);

    public List<Business> getListWithCondition(SearchDTO searchDTO);

    public List<Business> getList5(Criteria criteria);
    public Integer getTotal5();

    /* 작가 목록 */
    public List<Information> authorGetList(Criteria cri);
    public List<Information> getList(String img);

    /* 작가 총 수 */
    public int authorGetTotal(Criteria cri);

    int saveUser(Employee employee);

    @Delete("DELETE FROM BUSINESS_INFO_1 WHERE IMG_KEY = #{IMG_KEY}")
    int deleteContent(String key);

    @Delete("DELETE FROM INTEGRATION_INFO WHERE ELEMENTID = #{ELEMENTID}")
    int deleteContent2(String key);

    List<Information> getDeleteList();
    Business getDeleteSelect(String img);


    List<Employee> getUserList(); // User 테이블 가져오기
    void insertUser(Employee userVo); // 회원 가입
    Employee getUserByEmail(String email); // 회원 정보 가져오기
    Employee getUserById(String id);
    void updateUser(Employee userVo); // 회원 정보 수정
    void deleteUser(Long id); // 회원 탈퇴

}
