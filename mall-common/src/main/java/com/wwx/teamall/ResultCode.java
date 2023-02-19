package com.wwx.teamall;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {

    SUCCESS(200, "success"),
    FAILED(500, "failed");

    private Integer code;
    private String msg;
}
