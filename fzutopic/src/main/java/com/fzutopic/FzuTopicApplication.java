package com.fzutopic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan(basePackages = {"com.fzutopic.dao"})
public class FzuTopicApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzuTopicApplication.class, args);
    }

}
