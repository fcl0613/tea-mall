package com.wwx.teamall.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmItemVo {
    private Integer storeId;
    private String storeName;
    private List<OrderConfirmGoodsVo> goodsList;
}
