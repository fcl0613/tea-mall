package com.wwx.teamall.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderConfirmGoodsVo {
    private Integer goodsId;
    private String goodsCover;
    private String goodsName;
    private BigDecimal goodsPrice;
    private Integer goodsCount;
}
