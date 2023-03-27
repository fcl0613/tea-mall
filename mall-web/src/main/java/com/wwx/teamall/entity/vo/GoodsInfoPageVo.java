package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.DO.GoodsCommentsDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsInfoPageVo {
    private Integer goodsId;
    private String goodsName;
    private String subtitle;
    private List<String> goodsCoverList;
    private List<String> goodsDetailList;
    private BigDecimal price;
    private Integer stock;
    private Integer commentsCount;
    private List<GoodsCommentsDO> goodsCommentList;
}
