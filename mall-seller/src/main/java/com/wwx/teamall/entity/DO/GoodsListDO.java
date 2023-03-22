package com.wwx.teamall.entity.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsListDO {
    private Integer id;

    private String goodsName;

    private String goodsCover;

    /**
     * 副标题
     */
    private String subtitle;


    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;


    /**
     * 商品状态0下架1上架
     */
    private Integer goodsStatus;
}
