package com.allen.homeworkrelease.controller;

import com.allen.homeworkrelease.JavaLink;
import com.allen.homeworkrelease.common.result.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/api/homework")
public class HomeworkController {

    @GetMapping("/release")
    public RespResult<String> releaseHomework(){
        JavaLink.release();
        return RespResult.success();
    }

}
