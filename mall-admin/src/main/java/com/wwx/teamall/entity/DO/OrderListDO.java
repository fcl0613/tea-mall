package com.wwx.teamall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderListDO {
    private String orderId;
    private BigDecimal totalPrice;
    private String orderStatusStr;
    private Integer orderStatus;
    private LocalDateTime creatTime;

}
