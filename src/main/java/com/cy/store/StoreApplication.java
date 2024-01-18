package com.cy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;


//@Configuration
@SpringBootApplication
@MapperScan("com.cy.store.mapper")
//指定當前專案中的mapper介面的位置
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

//    @Bean
//    public MultipartConfigElement getMultipartConfigElement() {
//        //new 一個來設定的工廠
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //設定工廠接收的檔案大小,跟請求的大小
//        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
//        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
//        //通過工廠來創建一個 MultipartConfigElement
//        return factory.createMultipartConfig();
//    }
}
