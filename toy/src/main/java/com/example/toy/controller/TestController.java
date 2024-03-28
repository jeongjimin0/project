package com.example.toy.controller;

import com.example.toy.config.SettingConfig;
import com.example.toy.mapper.Mappers;
import com.example.toy.mapper.SearchDTO;
import com.example.toy.mapper.ViewVo;
import com.example.toy.model.*;
import com.example.toy.service.MapperService;
import com.example.toy.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.toy.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TestController {

    private final TestService testService;
    private final Mappers mapper;
    private final MapperService mapperService;
    private final ViewVo viewVo;
    private final ConnectController2 connectController2;
    private final SettingConfig settingConfig;

    public void setResult(String result) {
        this.result = result;
    }
    String result;

//    @GetMapping("/test")
//    public ResponseBody test(Model model) {
//        List<Connect> board = mapper.getMemo();
//        model.addAttribute("board", board);
//        return (ResponseBody) mapper.getMemo();
//
//    }


    @RequestMapping("/")
    public @ResponseBody String main(Model model) {
        settingConfig.setting();
        return "hi";

    }


//    등록 화면
    @RequestMapping("/toy/create")
    public String create() {

        return "create";
    }


    @GetMapping("/kk")
    public String main(Model model, Criteria cri) throws Exception {


        return "test";
    }

    @GetMapping("/test")
    public void x(@RequestPart("num") String num) throws Exception {
        System.out.println(num);
    }

    @GetMapping("/user")
    public String user(Model model, Criteria criteria){
        return "userPage";
    }



//    검색 화면
    @GetMapping("/toy")
    public String openPostList(Model model, Criteria cri, Information information) throws Exception {


        List<Information> list4 = testService.authorGetList(cri);
        model.addAttribute("list7", list4);


        /* 페이지 이동 인터페이스 데이터 */
        int total = testService.authorGetTotal(cri);
        PageDTO ex = new PageDTO(cri, total);
        PageDTO pageMaker = new PageDTO(cri, total);


        System.out.println(pageMaker);

        model.addAttribute("pageMaker", pageMaker);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("login", 1);

        } else {
            model.addAttribute("login", 2);
        }

            if (cri.getSearchType() != null) {
            if (cri.getSearchType().equals("C")) {
                List<Information> posts = testService.findAllPost(cri);
                model.addAttribute("list7", posts);

            } else if (cri.getSearchType().equals("T")) {

                List<Information> posts2 = testService.findAllPost2(cri);
                model.addAttribute("list7", posts2);


            } else if (cri.getSearchType().equals("F")) {
                List<Information> posts3 = testService.findAllPost3(cri);
                model.addAttribute("list7", posts3);


            } else if (cri.getSearchType().equals("A")) {
                List<Information> posts3 = testService.findAllPost4(cri);
                model.addAttribute("list7", posts3);

            }
        }

        return "mainpage";

        }


//    상세 화면
    @RequestMapping(value = "/toy/detail/{ELEMENTID}")
    public String detail(Model model, @PathVariable("ELEMENTID") String st, Num num) {

        Information list = mapperService.getBusiness_4(st);
        num.setDETAIL_CATEGORY(list.getCategory().getDETAIL_CATEGORY());
        num.setIMG_KEY(list.getIMG_KEY());


        List<Information> test = mapperService.getBusiness_7(num);
        model.addAttribute("list", test);
        model.addAttribute("list2", test.get(0).getIMG_KEY());


        connectController2.setSt(st);

        return "detail";
    }




    @RequestMapping(value = "/toy/delete")
    public String delete(Model model, @RequestParam(value = "IMG_KEY", required = false) List<String> st) {
        System.out.println("IMG_KEY : " + st);

        for (int i = 0; i < st.size(); i++) {
            String delete = st.get(i).substring(0, 15);

            System.out.println(delete);

            if (delete.contains("CO")) {
                int row = testService.deleteById(st.get(i).substring(0, 15));
            } else if (delete.contains("DP")) {
                int row = testService.deleteById2(st.get(i).substring(0, 15));
            } else {
                int row = testService.deleteById3(st.get(i).substring(0, 15));
            }
        }
        return "redirect:/toy";
    }

//    @GetMapping(value = "/toy/ss")
//    public void delete4(@RequestParam(value = "btnradio", required = false) String st) {
//        System.out.println(st);
//    }


//    @PostMapping("/test3")
//    public String add(Connect connect){
//        int row = testService.insertContent(connect);
//
//        return "test";
//    }


//    @PostMapping("/api/posts")
//    public @ResponseBody String save(@RequestPart(value = "key") Business business,
//                     @RequestPart(value = "image-file", required = false) MultipartFile file) throws  IOException {
//        System.out.println("목록");
//
//        int row = testService.insertContent(infor);
//        System.out.println(row);
//        return "gg";
//    }

//
//    @PostMapping("/test3")
//    public void add(Business business){
//        int row = testService.insertContent2(business);
//    }

    @GetMapping("/signUp")
    public String signUpForm() {
        return "userPage";
    }

    @PostMapping("/signUp")
    public String signUp(Employee employee) throws Exception {
        System.out.println(employee.getEMPLOYEE_NO());
        System.out.println(employee.getEMPLOYEE_PW());
        int result = testService.saveUser(employee);
        System.out.println(employee.getEMPLOYEE_PW());
        System.out.println(employee.getEMPLOYEE_NO());
        System.out.println(result);
        System.out.println("여기 1");
        return "redirect:/toy"; //로그인 구현 예정
    }


}
