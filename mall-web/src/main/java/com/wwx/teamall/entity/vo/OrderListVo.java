package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.DO.OrderItemDO;
import lombok.Data;

import java.util.List;

@Data
public class OrderListVo {
    private List<OrderItemDO> orderList;
    private Long total;
}
