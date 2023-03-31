package com.wwx.teamall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  OrderStatusEnum {
    WAITING_PAY(1, "待支付"),
    WAITING_SEND(2, "待发货"),
    WAITING_DELIVERY(3, "待收货"),
    FINISHED(4, "已完成"),
    CANCEL(5, "已取消");


    private Integer code;
    private String description;
}
