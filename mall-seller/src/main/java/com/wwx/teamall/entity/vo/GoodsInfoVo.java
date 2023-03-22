package com.wwx.teamall.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsInfoVo {
    private Integer id;

    private String goodsName;

    private String goodsCover;

    /**
     * 展示图片组逗号分割
     */
    private List<String> goodsShowPic;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 商品详情图片组逗号分割
     */
    private List<String> goodsDetails;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 分类父编号
     */
    private Integer categoryParentId;

    /**
     * 分类编号
     */
    private Integer categoryId;

    /**
     * 所属店铺编号
     */
    private Integer storeId;

    /**
     * 商品状态0下架1上架
     */
    private Integer goodsStatus;
}
