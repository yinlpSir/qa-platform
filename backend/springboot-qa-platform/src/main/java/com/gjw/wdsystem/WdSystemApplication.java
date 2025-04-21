package com.gjw.wdsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.gjw.wdsystem.mapper")
@EnableTransactionManagement // 开启事务注解支持
public class WdSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WdSystemApplication.class, args);
    }

}
