package com.wwx.teamall.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HomeContentVo {
    private Integer publishGoodsCount;
    private Integer goodsCount;
    private Integer orderCount;
    private Integer waitingSendOrderCount;
    private Integer waitingPayOrderCount;
    private Integer waitingDeliveryOrderCount;
    private BigDecimal totalIncome;
    private BigDecimal yesterdayIncome;
    private Integer yesterdayOrderCount;
}
