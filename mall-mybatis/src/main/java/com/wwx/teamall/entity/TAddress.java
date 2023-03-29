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
 * @since 2023-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TAddress implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String address;

    private Integer userId;

    /**
     * 联系人
     */
    private String linkName;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    private String provinceCode;

    private String cityCode;
    private String countyCode;
}
