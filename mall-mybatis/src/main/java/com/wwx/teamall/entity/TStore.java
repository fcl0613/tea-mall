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
 * @since 2023-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TStore implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String storeName;

    private Integer userId;

    private String phone;

    private String name;
}
