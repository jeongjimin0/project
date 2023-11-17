package com.example.shoppingmall.Controller;

import com.example.shoppingmall.Service.MemberService;
import com.example.shoppingmall.VO.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BookController bookController;
    @Autowired
    private BCryptPasswordEncoder pwEncoder;

    @RequestMapping("/member/login")
    public String loginGet() {
        return "login";
    }

    @RequestMapping("/member/join")
    public String joinGet() {
        return "join";
    }

    @PostMapping("/member/join")
    public String joinPost(MemberVO memberVO) {
        String rawPw = "";
        String encodePw = "";

        rawPw = memberVO.getMemberPw();
        encodePw = pwEncoder.encode(rawPw);
        memberVO.setMemberPw(encodePw);
        System.out.println(encodePw);

        memberService.addQuestion(memberVO);

        return "redirect:/main";
    }

    @PostMapping("/member/memberIdChk")
    @ResponseBody
    public String memberIdChkPOST(String memberId) throws Exception {
        int result = memberService.idCheck(memberId);
        if (result != 0) {
            return "fail";    // 중복 아이디가 존재
        } else {
            return "success";    // 중복 아이디 x
        } // memberIdChkPOST() 종료

    }

    @GetMapping("/mailCheck")
    @ResponseBody
    public String mailCheckGET(String email) throws Exception{
        System.out.println("인증번호 : " + email);
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
        String setFrom = "wjdwjd12341234@gmail.com";
        String toMail = email;
        String title = "회원가입 인증 이메일입니다.";
        String content = "홈페이지를 방문해 주셔서 감사합니다." + "<br><br>" + "인증번호는 " + "'" + checkNum + "'"+ "입니다." + "<br>" + "해당 인증번호를 인증번호 확인 란에 기입하여 주세요.";

        // 이메일 전송 코드
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);

        }catch(Exception e) {
            e.printStackTrace();
        }
        // ajax 를 통한 요청으로 인해 뷰로 다시 반환할 때 데이터 타입은 String 타입만 가능하다.
        String num = Integer.toString(checkNum);
        return num;
    }

    @PostMapping("/member/login")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr, Model model) throws Exception{
        // HttpServletRequest 는 로그인 성공 시 session 에 회원 정보를 저장하기 위해,
        // RedirectAttributes 는 로그인 실패 시 리다이렉트 된 로그인 페이지에 실패를 의미하는 데이터를 전송하기 위해 사용

        // 세션 사용하기 위한 선언
        HttpSession session = request.getSession();
        MemberVO lvo = memberService.memberLogin(member);


        if (lvo == null) {
            int result = 0;
            rttr.addFlashAttribute("result", result);
            model.addAttribute("login", 1);
            return "redirect:/member/login";

        }

        session.setAttribute("member", lvo);
        MemberVO mem = (MemberVO) session.getAttribute("member");
        bookController.setRes(mem);


        return "redirect:/main";
    }



}
