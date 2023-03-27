package com.wwx.teamall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsListDO {
    private Integer id;
    private String goodsName;
    private String goodsCover;
    private BigDecimal price;
}
