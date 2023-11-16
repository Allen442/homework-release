package com.allen.homeworkrelease.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author yinjunbiao
 * @version 1.0
 * @date 2023/11/4
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Payload {
    private String TargetDevice;

    private List<Homework> homeworks;
}
