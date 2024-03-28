package com.example.toy.model;


import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component

public class Information {



    private String ELEMENTID;
    private String IMG_KEY;
    private String MAIN_CATEGORY;
    private String DOC_CD;
    private String FILE_NM;
    private String FILE_EXT;
    private String RNUM;
    private String REGIST_USER_ID;
    private String UPDATE_USER_ID;
    private String REGIST_DATE;
    private String UPDATE_DATE;
    private String RETENTION_PERIOD;
    private String VALUE;
    private Business business;
    private Business2 business2;
    private Business3 business3;
    private Category category;


}
