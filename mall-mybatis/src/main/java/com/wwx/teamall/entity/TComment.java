package com.wwx.teamall.entity;

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
 * @since 2023-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TComment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderId;

    private Integer storeId;

    private Integer goodsId;

    private Integer userId;

    private String userName;

    private String goodsName;

    private String content;

    private String userAvatar;

    private LocalDateTime createTime;


}
