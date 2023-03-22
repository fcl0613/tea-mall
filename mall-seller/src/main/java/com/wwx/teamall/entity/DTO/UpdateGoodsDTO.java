package com.wwx.teamall.entity.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateGoodsDTO {
    private Integer id;
    private String goodsName;
    private String goodsCover;
    private List<String> goodsShowPic;
    private List<String> goodsDetails;
    private String subtitle;
    private BigDecimal price;
    private Integer stock;
    private Integer categoryParentId;
    private Integer categoryId;
    private Integer goodsStatus;
}
