package com.muli.picturemanage;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.muli.picturemanage.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class PictureManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureManageApplication.class, args);
    }

}
