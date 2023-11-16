package com.allen.homeworkrelease.controller;

import com.allen.homeworkrelease.JavaLink;
import com.allen.homeworkrelease.common.result.RespResult;
import com.allen.homeworkrelease.pojo.Homework;
import com.allen.homeworkrelease.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("/api/homework")
public class HomeworkController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/release")
    public RespResult<String> releaseHomework(){
        JavaLink.release(null);
        return RespResult.success();
    }

    @PostMapping("test")
    public RespResult<String> recovert(@RequestBody List<Homework> homeworks){
        return homeService.saveAll(homeworks);
    }

}
