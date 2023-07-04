package com.wwx.teamall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  UserRoleEnum {
    COMMON_USER(0, "普通用户"),
    TEA_GROWER(1, "茶农");

    private Integer code;
    private String description;
}
