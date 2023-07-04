package com.wwx.teamall.model;

import com.wwx.teamall.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static <T>Result<T> success(T data) {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T>Result<T> success(String msg, T data) {
        return new Result(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static Result failed() {
        return new Result(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg(), null);
    }

    public static Result failed(String msg) {
        return new Result(ResultCode.FAILED.getCode(), msg, null);
    }
}
