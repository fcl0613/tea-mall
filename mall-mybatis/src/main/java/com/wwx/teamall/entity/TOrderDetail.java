package com.wwx.teamall.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wwx
 * @since 2023-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderId;

    private Integer goodsId;

    private Integer goodsCount;

    private String goodsName;

    private String goodsCover;

    private BigDecimal goodsPrice;

    private LocalDateTime createTime;

    /**
     * 订单完成后的评论标志
     */
    private Integer hasCommented;

    private Integer userId;
}
