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

    @PostMapping("/member/login.do")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr, Model model) throws Exception {
        // HttpServletRequest 는 로그인 성공 시 session 에 회원 정보를 저장하기 위해,
        // RedirectAttributes 는 로그인 실패 시 리다이렉트 된 로그인 페이지에 실패를 의미하는 데이터를 전송하기 위해 사용

        // 세션 사용하기 위한 선언

        String rawPw = "";
        String encodePw = "";
        HttpSession session = request.getSession();

        MemberVO lvo = memberService.memberLogin(member);

        if (lvo != null) { // 일치하는 아이디 존재 시
            rawPw = member.getMemberPw(); // 사용자가 제출한 비밀번호
            encodePw = lvo.getMemberPw(); // 데이터베이스에 저장한 인코딩된 비밀번호

            if (true == pwEncoder.matches(rawPw, encodePw)) { // 비밀번호 일치 판단

                lvo.setMemberPw(""); // 인코딩된 비밀번호 정보 지움
                session.setAttribute("member", lvo); // session 에 사용자의 정보 저장
                bookController.setRes(lvo); // 로그인 된 session 을 넘겨줌
                return "redirect:/main"; // 메인페이지 이동

            } else {
                rttr.addFlashAttribute("result", 0);
                return "redirect:/member/login"; // 로그인 페이지 이동
            }
        } else { // 일치하는 아이디가 존재하지 않을 시 (로그인 실패)
            rttr.addFlashAttribute("result", 0);
            return "redirect:/member/login";
        }

    }


    @GetMapping("/member/logout")
    public String logoutMainGet(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.invalidate();
        bookController.setRes(null);
        return "redirect:/main";
    }

    /* 비동기방식 로그아웃 메서드 */
    @RequestMapping(value="logout.do", method=RequestMethod.POST)
    @ResponseBody // ajax 를 통해서 서버에 요청을 하는 방식이기 때문에 해당 메서드에 반드시 @ResponseBody 어노테이션을 붙여줘야 합니다.
    public void logoutPOST(HttpServletRequest request) throws Exception{

        System.out.println("비동기 메서드 진입");
        HttpSession session = request.getSession();
        session.invalidate();
        bookController.setRes(null);

    }


}
