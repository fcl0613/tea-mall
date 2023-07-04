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
 * @since 2023-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String idCard;

    /**
     * 0未知1男2女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 0普通用户1茶农
     */
    private Integer role;

    private String birthday;

    private String nickName;
}
