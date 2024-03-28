package com.example.toy.config;

import com.example.toy.model.Business;
import com.example.toy.model.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.example.toy.mapper.Mappers;

import java.util.List;

@Component
public class SettingConfig {

    @Autowired Mappers mappers;
    @Autowired Business business;


    public void setting() {
        List<Information> list = mappers.getDeleteList();

        for (int i = 0; i < 5; i++) {
            Business select = mappers.getDeleteSelect(list.get(i).getIMG_KEY());

            if (select == null) {
                System.out.println("비었다");

                if (list.get(i).getIMG_KEY().contains("CO")){
                    mappers.UpdateById(list.get(i).getIMG_KEY());
                } else if (list.get(i).getIMG_KEY().contains("DP")) {

                } else {

                }

            } else {
                System.out.println("안비었다");
            }
        }


    }
}
