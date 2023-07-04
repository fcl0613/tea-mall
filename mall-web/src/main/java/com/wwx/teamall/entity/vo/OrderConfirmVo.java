package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TAddress;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderConfirmVo {
    private List<TAddress> addressList;
    private List<OrderConfirmItemVo> orderConfirmItemList;
    private BigDecimal totalPrice;
    private List<Integer> cartIds;
}
