package com.wwx.teamall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoodsHasCommentEnum {

    ORDER_UN_FINISH(0, "订单未完成"),
    NO_COMMENTED(1, "未评论"),
    COMMENTED(2, "已评论");


    private Integer code;
    private String description;
}
