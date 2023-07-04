package com.wwx.teamall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BannerPublishEnum {
    PUBLISH(0, "发布"),
    UN_PUBLISH(1, "不发布");

    private Integer code;
    private String description;
}
