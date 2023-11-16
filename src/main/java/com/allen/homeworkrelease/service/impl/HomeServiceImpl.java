package com.allen.homeworkrelease.service.impl;

import com.alibaba.fastjson.JSON;
import com.allen.homeworkrelease.JavaLink;
import com.allen.homeworkrelease.common.result.RespResult;
import com.allen.homeworkrelease.mapper.HomeworkMapper;
import com.allen.homeworkrelease.pojo.Homework;
import com.allen.homeworkrelease.service.HomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HomeServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeService {


    private String device = "";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public RespResult<String> saveAll(List<Homework> homeworks) {
        homeworks.parallelStream().forEach(homework -> {
            homework.setReleaseDatetime(LocalDateTime.now().format(formatter));
            homework.setTargetDevice(device);
        });
        saveBatch(homeworks);
        JavaLink.release(homeworks);
        return RespResult.success();
    }
}
