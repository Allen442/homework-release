package com.allen.homeworkrelease.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Homework {
    private String subject;

    private String homework;

    @TableField("release_datetime")
    private String releaseDatetime;

    @TableField("target_device")
    private String targetDevice;

}
