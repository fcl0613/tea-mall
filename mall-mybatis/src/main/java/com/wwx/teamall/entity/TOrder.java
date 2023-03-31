package com.wwx.teamall.entity;

import java.math.BigDecimal;
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
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private Integer userId;

    private Integer storeId;

    private BigDecimal totalPrice;

    private Integer orderStatus;

    private LocalDateTime createTime;

    private String deliveryName;

    private String deliveryPhone;

    private String provice;

    private String city;

    private String county;

    private String deliveryAddress;


}
