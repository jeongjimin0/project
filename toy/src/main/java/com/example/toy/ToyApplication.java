package com.example.toy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.example.toy.mapper")
@SpringBootApplication
public class ToyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyApplication.class, args);
	}

}
