package com.allen.homeworkrelease.service;

import com.allen.homeworkrelease.common.result.RespResult;
import com.allen.homeworkrelease.pojo.Homework;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface HomeService extends IService<Homework> {
    RespResult<String> saveAll(List<Homework> homeworks);
}
