package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.TAddress;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-29
 */
public interface TAddressService extends IService<TAddress> {

    Result addAddress(TAddress address);

    Result updateAddress(TAddress address);

    Result getAddressList();

    Result deleteAddress(Integer id);

    Result getAddressDetail(Integer id);
}
