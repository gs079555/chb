package com.jiufukameng;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@MapperScan("com.jiufukameng.mapper")
public class ChbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChbApplication.class, args);
	}
	@Bean
	public MultipartConfigElement a() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//最大上传大小
		DataSize maxFileSize = DataSize.ofMegabytes(50);
		factory.setMaxFileSize(maxFileSize);;
		//最大请求大小
		DataSize maxRequestSize = DataSize.ofMegabytes(100);
		factory.setMaxRequestSize(maxRequestSize);
		
		return factory.createMultipartConfig();
	}
	

}
