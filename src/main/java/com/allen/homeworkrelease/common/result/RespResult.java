package com.allen.homeworkrelease.common.result;


import com.allen.homeworkrelease.common.constant.ResultConstant;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RespResult<T> {
    private Integer code;

    private boolean success;
    private String message;
    private T data;

    public RespResult(Integer code, boolean isSuccess, String message) {
        this.code = code;
        this.success = isSuccess;
        this.message = message;
    }

    public RespResult() {
    }

    public RespResult(Integer code, boolean isSuccess, String message, T data) {
        this.code = code;
        this.success = isSuccess;
        this.message = message;
        this.data = data;
    }

    public static <T> RespResult<T> success(T data) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, true, ResultConstant.OPERATE_SUCCESS_MESSAGE, data);
    }

    public static <T> RespResult<T> failed(T data) {
        return new RespResult<>(ResultConstant.FAIL_CODE, false, ResultConstant.OPERATE_FAIL_MESSAGE, data);
    }

    public static <T> RespResult<T> success() {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, true, ResultConstant.OPERATE_SUCCESS_MESSAGE);
    }

    public static <T> RespResult<T> failed() {
        return new RespResult<>(ResultConstant.FAIL_CODE, false, ResultConstant.OPERATE_FAIL_MESSAGE);
    }

    public static <T> RespResult<T> successWithMsg(String message, T data) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, true, message, data);
    }

    public static <T> RespResult<T> failedWithMsg(String message, T data) {
        return new RespResult<>(ResultConstant.FAIL_CODE, false, message, data);
    }

    public static <T> RespResult<T> successWithMsg(String message) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE, true, message);
    }

    public static <T> RespResult<T> failedWithMsg(String message) {
        return new RespResult<>(ResultConstant.FAIL_CODE, false, message);
    }

    public static <T> RespResult<T> failedWithCodeAndMsg(int code, String message) {
        return new RespResult<>(code, false, message);
    }


}
