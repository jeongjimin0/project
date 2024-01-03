package com.example.shoppingmall.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/main")
    public String adminMainGET() throws Exception {
        logger.info("관리자 페이지 이동");
        return "admin_main";
    }

    /* 상품 등록 페이지 접속 */
    @GetMapping("/goodsManage")
    public void goodsManageGET() throws Exception{
        logger.info("상품 등록 페이지 접속");
    }

    /* 상품 등록 페이지 접속 */
    @GetMapping("/goodsEnroll")
    public void goodsEnrollGET() throws Exception{
        logger.info("상품 등록 페이지 접속");
    }

    /* 작가 등록 페이지 접속 */
    @GetMapping("/authorEnroll")
    public String authorEnrollGET() throws Exception{
        logger.info("작가 등록 페이지 접속");

        return "authorEnroll";
    }

    /* 작가 관리 페이지 접속 */
    @GetMapping("/authorManage")
    public void authorManageGET() throws Exception{
        logger.info("작가 관리 페이지 접속");
    }
}
