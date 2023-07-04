package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.TOrderDetail;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-31
 */
public interface TOrderDetailService extends IService<TOrderDetail> {

    Result getOrderDetailList();
}
