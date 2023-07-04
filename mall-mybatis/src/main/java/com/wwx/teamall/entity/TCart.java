package com.wwx.teamall.entity;

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
 * @since 2023-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TCart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goodsId;

    private Integer userId;

    /**
     * 商品数量
     */
    private Integer goodsCount;


}
