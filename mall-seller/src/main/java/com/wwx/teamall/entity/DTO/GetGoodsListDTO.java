package com.wwx.teamall.entity.DTO;

import lombok.Data;

@Data
public class GetGoodsListDTO {
    private String goodsName;
    private Integer categoryId;
    private Integer goodsStatus;
    private Long pageNum;
    private Long pageSize;
}
