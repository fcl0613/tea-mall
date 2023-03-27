package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.TCart;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-27
 */
public interface TCartService extends IService<TCart> {

    Result addCart(Integer goodsId);

    Result updateCount(Integer id, Integer count);

    Result getCartList();

    Result deleteCart(Integer id);
}
