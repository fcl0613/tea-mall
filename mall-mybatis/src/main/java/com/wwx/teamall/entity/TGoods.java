package com.wwx.teamall.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wwx
 * @since 2023-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TGoods implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String goodsName;

    private String goodsCover;

    /**
     * 展示图片组逗号分割
     */
    private String goodsShowPic;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 商品详情图片组逗号分割
     */
    private String goodsDetails;

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

    private Integer sales;
}
