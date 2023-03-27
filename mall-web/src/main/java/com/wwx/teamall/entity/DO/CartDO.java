package com.wwx.teamall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDO {
    private Integer id;
    private Integer goodsId;
    private String goodsName;
    private String goodsCover;
    private BigDecimal price;
    private Integer goodsCount;
}
