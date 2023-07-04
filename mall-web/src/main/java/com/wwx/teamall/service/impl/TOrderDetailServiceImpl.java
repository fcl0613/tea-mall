package com.wwx.teamall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.TOrderDetail;
import com.wwx.teamall.enums.GoodsHasCommentEnum;
import com.wwx.teamall.mapper.TOrderDetailMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TOrderDetailService;
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
 * @since 2023-03-31
 */
@Service
public class TOrderDetailServiceImpl extends ServiceImpl<TOrderDetailMapper, TOrderDetail> implements TOrderDetailService {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Result getOrderDetailList() {
        List<TOrderDetail> list = this.list(new LambdaQueryWrapper<TOrderDetail>()
                .eq(TOrderDetail::getUserId, getUserId())
                .eq(TOrderDetail::getHasCommented, GoodsHasCommentEnum.NO_COMMENTED.getCode()));
        return Result.success(list);
    }

    private Integer getUserId() {
        HttpServletRequest request = HttpUtil.getRequest();
        String authorization = request.getHeader("Authorization");
        return jwtUtil.getUserId(authorization);
    }
}
