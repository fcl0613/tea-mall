package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.DTO.ConfirmOrderDTO;
import com.wwx.teamall.entity.DTO.CreateOrderDTO;
import com.wwx.teamall.entity.TOrder;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-31
 */
public interface TOrderService extends IService<TOrder> {

    Result confirmOrder(ConfirmOrderDTO confirmOrderDTO);

    Result createOrder(CreateOrderDTO createOrderDTO);

    Result directConfirm(Integer id, Integer count);

    Result directBuy(Integer id, Integer count, Integer addressId);
}
