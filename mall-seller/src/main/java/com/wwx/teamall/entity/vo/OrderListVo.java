package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.DO.OrderListDO;
import lombok.Data;

import java.util.List;

@Data
public class OrderListVo {
    private List<OrderListDO> list;
    private Long total;
}
