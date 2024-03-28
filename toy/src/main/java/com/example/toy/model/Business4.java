package com.example.toy.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Business4 {

    private String CUST_NM;
    private String RRN_NO;
    private String VALUE;

}
