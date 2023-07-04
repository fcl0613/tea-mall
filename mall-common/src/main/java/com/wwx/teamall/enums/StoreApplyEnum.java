package com.wwx.teamall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreApplyEnum {

    PENDING(0, "未处理"),
    AGREE(1, "同意"),
    REFUSE(2, "拒绝");

    private Integer code;
    private String description;
}
