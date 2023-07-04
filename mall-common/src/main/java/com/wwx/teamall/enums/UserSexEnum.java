package com.wwx.teamall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserSexEnum {

    UN_KNOW(0, "未知"),
    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer code;
    private String description;
}
