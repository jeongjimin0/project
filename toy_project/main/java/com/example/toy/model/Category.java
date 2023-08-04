package com.example.toy.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Category {
    private String DOC_CD;
    private String MAIN_CATEGORY;
    private String MAIN_CD;
    private String MEDIUM_CATEGORY;
    private String MEDIUM_CD;
    private String DETAIL_CATEGORY;
    private String DETAIL_CD;
    private String RETENTION_PERIOD;
    private String REGIST_DATE;
    private String UPDATE_DATE;
}
