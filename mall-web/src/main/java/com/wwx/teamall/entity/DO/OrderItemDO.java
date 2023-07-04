package com.wwx.teamall.entity.DO;

import com.wwx.teamall.entity.TOrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderItemDO {
    private String orderId;
    private LocalDateTime createTime;
    private Integer status;
    private String statusStr;
    private String deliveryName;
    private BigDecimal totalPrice;
    private List<TOrderDetail> goodsList;
}
