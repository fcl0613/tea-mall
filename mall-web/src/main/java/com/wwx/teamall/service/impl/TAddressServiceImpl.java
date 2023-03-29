package com.wwx.teamall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.TAddress;
import com.wwx.teamall.mapper.TAddressMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TAddressService;
import com.wwx.teamall.utils.HttpUtil;
import com.wwx.teamall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-29
 */
@Service
public class TAddressServiceImpl extends ServiceImpl<TAddressMapper, TAddress> implements TAddressService {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Result addAddress(TAddress address) {
        address.setUserId(getUserId());
        this.save(address);
        return Result.success();
    }

    @Override
    public Result updateAddress(TAddress address) {
        this.updateById(address);
        return Result.success();
    }

    @Override
    public Result getAddressList() {
        List<TAddress> list = this.list();
        return Result.success(list);
    }

    @Override
    public Result deleteAddress(Integer id) {
        this.removeById(id);
        return Result.success();
    }

    @Override
    public Result getAddressDetail(Integer id) {
        return Result.success(this.getById(id));
    }

    private Integer getUserId () {
        HttpServletRequest request = HttpUtil.getRequest();
        String authorization = request.getHeader("Authorization");
        return jwtUtil.getUserId(authorization);
    }
}
