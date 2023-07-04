package com.wwx.teamall.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class HomeCategoryVo {
    private Integer id;
    private String categoryName;
    private List<HomeCategoryVo> children;
    private List<HomeGoodsVo> GoodsList;
}
