package com.allen.homeworkrelease.common.constant;

/**
 * @author Allen
 */
public interface ResultConstant {

    String OPERATE_SUCCESS_MESSAGE = "success";
    String OPERATE_FAIL_MESSAGE = "fail";
    String OPERATE_NO_POWER = "no power";

    int SUCCESS_CODE = 200;
    int FAIL_CODE = 400;
    int FORBIDDEN_CODE = 403;

    /**
     * 登录密码错误次数过多错误码
     */
    int MULTIPLE_PASSWORD_ERRORS_CODE = 4001;
}
