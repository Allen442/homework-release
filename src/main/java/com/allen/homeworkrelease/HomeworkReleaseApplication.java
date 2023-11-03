package com.allen.homeworkrelease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  HomeworkReleaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkReleaseApplication.class, args);
        JavaLink.initLink();
    }

}
