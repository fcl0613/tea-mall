package com.wwx.teamall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoodsHasCommentEnum {

    NO_COMMENTED(0, "未评论"),
    HAS_COMMENTED(1, "已评论");


    private Integer code;
    private String description;
}
