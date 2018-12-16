package com.dwd.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnableScheduling 启动扫描注入Swagger
 * @MapperScan 启动mapper扫描路径
 * Created by Chen WenJie on 2018/12/15.
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableScheduling
@MapperScan("com.dwd.www.db.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
