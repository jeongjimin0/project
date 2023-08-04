package com.example.toy.controller;


import com.example.toy.config.AES256;
import com.example.toy.mapper.Mappers;
import com.example.toy.mapper.ViewVo;
import com.example.toy.model.*;
import com.example.toy.service.MapperService;
import com.example.toy.service.TestService;
import com.example.toy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.File;
import java.util.List;

@Controller
@PropertySource("classpath:api.properties")
public class ConnectController2 {
    private static asysConnectData con = null;

    // Autowired 의존성 주입
    @Autowired
    Information information;
    @Autowired
    Business business;
    @Autowired
    Category category;
    @Autowired
    private TestService testService;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private Mappers mappers;
    @Autowired
    private ViewVo viewVo;
    @Autowired Business4 business4;
    @Autowired UserService userService;

    // 필요한 변수
    private String hostname, description, id, pw;
    private String st;
    private String ext;
    private int port;
    private int result;


    // 생성자 -> 프로퍼티를 가지고 와서 사용
    public ConnectController2(@Value("${Hostname}") String hostname, @Value("${port}") int port, @Value("${Description}") String description,
                              @Value("${ID}") String id, @Value("${Pw}") String pw) {

        this.hostname = hostname;
        this.port = port;
        this.description = description;
        this.id = id;
        this.pw = pw;

        con = new asysConnectData("192.168.100.35", port, description, id, pw);
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public void discon() {
        if (con != null) {
            con.close();
            con = null;
        }
    }




    // 등록
    @PostMapping("/toy/create")
    @ResponseBody
    public String stream(@RequestParam("image-file") List<MultipartFile> multipartFile,
                         @ModelAttribute("business4") final Business4 business4,

                         RedirectAttributes redirectAttributes, Model model,

//                         @RequestParam("IMG_KEY") String img,
                         @RequestParam(value = "sido1", required = false) List<String> value,
                         @RequestParam(value = "DETAIL_CATEGORY", required = false) List<String> DETAIL_CATEGORY,
                         @RequestParam(value = "CUST_NM", required = false) String CUST_NM,
                         @RequestParam(value = "RRN_NO", required = false) String RRN_NO,
                         Business business, Business2 business2, Business3 business3, Information information, Num Number

    ) throws Exception {

        // saveFile 경로. Windows
//        File saveFile = new File("C:/test/test");
        // saveFile 경로. Linux
        File saveFile = new File(System.getProperty("user.home") + "/Downloads/test");
        asysUsrElement uePage1 = new asysUsrElement(con);
        // 주민번호 암호화 인스턴스
        AES256 aes256 = new AES256();

        // id 에 로그인 인증을 한 id를 넣는다.
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee userVo = userService.getUserById(id);

        int index = multipartFile.size();


        String message = "";

        // union 가급적 사용하지 않기 위해 if 문으로 mybatis 의 컬럼에 대입
        if (value.get(0).equals("공통")) {
            business4.setVALUE("BUSINESS_INFO_1");
            System.out.println("getValue : " + business4.getVALUE());

        } else if (value.get(0).equals("수신")) {
            business4.setVALUE("BUSINESS_INFO_2");
            System.out.println("getValue : " + business4.getVALUE());

        } else {
            business4.setVALUE("BUSINESS_INFO_3");
            System.out.println("getValue : " + business4.getVALUE());
        }


        // 업무 테이블에 해당하는 고객 이름, 주민번호로 해당하는 img_key 여부 확인
        List<Business> ee = testService.findById_6(business4);


        // category 의 대분류에 입력 받은 대분류를 가지고 옴
        category.setMAIN_CATEGORY(value.get(0));



        // 테이블에 img_key 여부를 확인하여 비어있을 시 업무 테이블 데이터 추가
        for (int i = 0; i < DETAIL_CATEGORY.size(); i++) {
            Number.setMAIN_CATEGORY(value.get(i));
            Number.setDETAIL_CATEGORY(DETAIL_CATEGORY.get(i));

            // 업무 테이블에 해당 img_key 가 비어있을 시
            if (ee.isEmpty() == true) {
                if (value.get(0).equals("공통")) {
                    information.setMAIN_CATEGORY("공통");
                    Category code = this.mapperService.fineView(Number);
                    business.setREGIST_USER_ID(userVo.getEMPLOYEE_NO());
                    business.setUPDATE_USER_ID(userVo.getEMPLOYEE_NO());
                    business.setRETENTION_PERIOD(code.getRETENTION_PERIOD().replace("일", ""));
                    int row2 = testService.insertContent2(business);

                } else if (value.get(0).equals("수신")) {
                    information.setMAIN_CATEGORY("수신");
                    Category code = this.mapperService.fineView(Number);
                    business2.setREGIST_USER_ID(userVo.getEMPLOYEE_NO());
                    business2.setUPDATE_USER_ID(userVo.getEMPLOYEE_NO());
                    business2.setRETENTION_PERIOD(code.getRETENTION_PERIOD().replace("일", ""));
                    int row2 = testService.insertContent3(business2);

                } else {
                    information.setMAIN_CATEGORY("여신");
                    Category code = this.mapperService.fineView(Number);
                    business3.setREGIST_USER_ID(userVo.getEMPLOYEE_NO());
                    business3.setUPDATE_USER_ID(userVo.getEMPLOYEE_NO());
                    business3.setRETENTION_PERIOD(code.getRETENTION_PERIOD().replace("일", ""));
                    int row2 = testService.insertContent4(business3);
                }
                // 업무 테이블에 해당 img_key 가 있을 시
            } else {
                System.out.println("이미 있는 파일입니다.");
            }
        }

        // 파라미터로 사용되는 객체에 암호화 된 주민번호 입력
        business4.setRRN_NO(business.getRRN_NO());

        // 업무 테이블 데이터 추가 후의 리스트
        List<Business> after = testService.findById_6(business4);

        // img_key 를 img 변수 안에 대입
        String img = after.get(0).getIMG_KEY();
//        createConfig.createConfig(value, DETAIL_CATEGORY, CUST_NM, RRN_NO);


        // img_key 에 해당 문자가 포함되있을 시 IF 문
        if(img.contains("CO")) {
            uePage1.m_descr = "C0_ARC_1ST";
            uePage1.m_cClassId = "CO_CC";
            uePage1.m_userSClass = "SUPER";
            uePage1.m_eClassId = "IMAGE";
        } else if (img.contains("DP")) {
            uePage1.m_descr = "DP_ARC_1ST";
            uePage1.m_cClassId = "DP_CC";
            uePage1.m_userSClass = "SUPER";
            uePage1.m_eClassId = "IMAGE";
        } else if (img.contains("LN")) {
            uePage1.m_descr = "LN_ARC_1ST";
            uePage1.m_cClassId = "LN_CC";
            uePage1.m_userSClass = "SUPER";
            uePage1.m_eClassId = "IMAGE";
        } else {
            uePage1.m_descr = "Main Archive";
            uePage1.m_cClassId = "BASIC";
            uePage1.m_userSClass = "SUPER";
            uePage1.m_eClassId = "IMAGE";
        }


        // setting file
        String gateway = "XTORM_MAIN";



        // 안 for 문에 사용할 숫자 변수 선언
        int num = 0;

        try {

            // MultipartFile 을 file 로 변환

            for (MultipartFile file : multipartFile) {
                System.out.println(multipartFile.size());
                value.add(value.get(0));
                // file 안에 담긴 실제 파일을 savaFile 변수에 옮겨준다.
                file.transferTo(saveFile);
                // api 의 경로를 saveFile 경로로 대입
                uePage1.m_localFile = saveFile.getAbsolutePath();
                redirectAttributes.addFlashAttribute("message", "Successfully stream!" + file.getOriginalFilename() + "!");

                // ext 에 확장자 대입
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

                // 확장자 제어
                if (ext.equals("jpg") || ext.equals("doc") || ext.equals("png") || ext.equals("pdf") || ext.equals("hwp") || ext.equals("txt") ||
                    ext.equals("tif") || ext.equals("pptx") || ext.equals("docx") || ext.equals("ppt") || ext.equals("xlsx") ||
                    ext.equals("xls") || ext.equals("bmp")) {

                    // xtorm 생성
                    int ret = uePage1.create(gateway);

                        Number.setMAIN_CATEGORY(value.get(num));


                        if (multipartFile.size() != 1 ) {
                            int limit = DETAIL_CATEGORY.size();
                            Number.setDETAIL_CATEGORY(DETAIL_CATEGORY.get(num));


                            for (int i = DETAIL_CATEGORY.size(); i < limit + multipartFile.size() - 2; i++) {
                                DETAIL_CATEGORY.add(i, DETAIL_CATEGORY.get(num));

                                Number.setDETAIL_CATEGORY(DETAIL_CATEGORY.get(num));
                                System.out.println(DETAIL_CATEGORY);


                                break;
                            }

                        } else {
                            Number.setDETAIL_CATEGORY(DETAIL_CATEGORY.get(num));
                        }

                    Category code = this.mapperService.fineView(Number);

                    information.setDOC_CD(code.getDOC_CD());
                    information.setMAIN_CATEGORY(value.get(num));

                    String test = code.getRETENTION_PERIOD().replace("일", "");

                    /* xtorm data 생성 실패 시 */
                    if (ret != 0) {
                        System.out.println("Error, create stream, " + uePage1.getLastError());

                        // xtorm 생성 실패 시 : 업무 테이블의 데이터가 새로 생성되었다면 삭제
                        if (ee.isEmpty() == true) {
                            mappers.deleteContent(after.get(0).getIMG_KEY());
                        }
                        message = "<script>alert('등록이 실패하였습니다.');location.href='/toy/create';</script>";

                    /* xtorm data 생성 성공 시 */
                    } else if (ret == 0) {

                        System.out.println("Success, create stream, " + uePage1.m_elementId);

                        /* 통합 테이블에 데이터 입력 */
                        information.setELEMENTID(uePage1.m_elementId.substring(12, 28));
                        information.setFILE_EXT(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
                        information.setFILE_NM(file.getOriginalFilename());
                        information.setIMG_KEY(img);
                        information.setREGIST_USER_ID(userVo.getEMPLOYEE_NO());
                        information.setUPDATE_USER_ID(userVo.getEMPLOYEE_NO());
                        information.setRETENTION_PERIOD(code.getRETENTION_PERIOD().replace("일", ""));

                        int row = testService.insertContent(information);
                        result = row;

                        num = num + 1;
                        saveFile.delete();
                        code = null;


                        message = "<script>alert('Success');location.href='/toy';</script>";
                    }
                }
                else {
                    message = "<script>alert('잘못된 확장자입니다.');location.href='/toy/create';</script>";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         finally {
            // 임시저장해 두었던 파일 삭제
            saveFile.delete();

            // 통합 테이블이 생성되지 않았을 시 업무 테이블 삭제
            if (result == 0) {
                if (ee.isEmpty() == true) {
                    int delete = mappers.deleteContent(after.get(0).getIMG_KEY());
                }
                uePage1.m_elementId = "XTORM_MAIN::" + uePage1.m_elementId + "::IMAGE";
                int ret = uePage1.delete();
            }
        }
        return message;
    }


    @GetMapping("/toy/download")
    public String download(Model model, @RequestParam(value = "ELEMENTID", required = false) String kk) {


        asysUsrElement uePage1 = new asysUsrElement(con);
        // 다운로드할 element id 를 저장
        String elementId = kk;



        uePage1.m_elementId = "XTORM_MAIN::" + elementId + "::IMAGE";

        //다운로드될 파일 이름
        String user_home = System.getProperty("user.home");
        String localfile = user_home + "/Downloads/" + kk;
        viewVo.setView(kk);

        int ret = uePage1.getContent(localfile);

        if (ret != 0) {
            System.out.println("Error, download normal, " + uePage1.getLastError());

        } else {
            System.out.println("Success, download normal, " + uePage1.m_elementId);
        }
        return "detail";

    }

    @GetMapping("/delete2")
    public @ResponseBody String delete2(@RequestParam("deleteid") String delete) {

        asysUsrElement uePage1 = new asysUsrElement(con);
        String elementId = delete;
        uePage1.m_elementId = "XTORM_MAIN::" + elementId + "::IMAGE";
        int ret = uePage1.delete();

        // 이해와 주석 = ret 부분의 코드 (?)
        if (ret != 0) {
            System.out.println("Error, failed to delete, " + uePage1.getLastError());
            return "fail";
        } else {
            System.out.println("Success, delete is done, " + uePage1.m_elementId);
            return "delete!";
        }

    }

}