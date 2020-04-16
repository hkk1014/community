package com.hk.community.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@MapperScan(basePackages="com.hk.community.community.mapper",sqlSessionFactoryRef="sqlSessionFactory")


@MapperScan("com.hk.community.community.mapper")
@SpringBootApplication
@EnableScheduling//开启定时任务
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
