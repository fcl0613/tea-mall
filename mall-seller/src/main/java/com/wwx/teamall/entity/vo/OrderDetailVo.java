package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TOrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDetailVo {
    private String orderId;
    private Integer status;
    private String statusStr;
    private String deliveryName;
    private String deliveryPhone;
    private String address;
    private BigDecimal totalPrice;
    private List<TOrderDetail> goods;
}
