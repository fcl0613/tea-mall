package com.wwx.teamall.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HomeGoodsVo {
    private Integer id;
    private String goodsName;
    private BigDecimal price;
    private String goodsCover;
}
