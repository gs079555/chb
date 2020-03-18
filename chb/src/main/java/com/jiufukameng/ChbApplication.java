package com.jiufukameng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiufukameng.mapper")
public class ChbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChbApplication.class, args);
	}

}
