package com.example.toy.model;


import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Business2 {

    private String IMG_KEY;
    private String CUST_NM;
    private String RRN_NO;
    private String DEL_YN;
    private String RETENTION_PERIOD;
    private String REGIST_USER_ID;
    private String UPDATE_USER_ID;
    private String REGIST_DATE;
    private String UPDATE_DATE;




}
