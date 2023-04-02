package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.DTO.GetOrderListDTO;
import com.wwx.teamall.entity.TOrder;
import com.wwx.teamall.model.Result;

public interface OrderService extends IService<TOrder> {
    Result getOrderList(GetOrderListDTO dto);

    Result getOrderDetail(String id);

    Result cancelOrder(String id);

    Result deleteOrder(String id);
}
