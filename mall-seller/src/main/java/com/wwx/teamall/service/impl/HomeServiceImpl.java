package com.wwx.teamall.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwx.teamall.entity.TGoods;
import com.wwx.teamall.entity.TOrder;
import com.wwx.teamall.entity.vo.HomeContentVo;
import com.wwx.teamall.enums.OrderStatusEnum;
import com.wwx.teamall.mapper.TGoodsMapper;
import com.wwx.teamall.mapper.TOrderMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.HomeService;
import com.wwx.teamall.utils.HttpUtil;
import com.wwx.teamall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private TGoodsMapper goodsMapper;

    @Autowired
    private TOrderMapper orderMapper;


    @Override
    public Result getHomeContent() {
        HomeContentVo homeContentVo = new HomeContentVo();
        Integer storeId = getStoreId();
        Integer publishGoodsCount = goodsMapper.selectCount(new LambdaQueryWrapper<TGoods>()
                .eq(TGoods::getStoreId, storeId)
                .eq(TGoods::getGoodsStatus, 1));
        homeContentVo.setPublishGoodsCount(publishGoodsCount);
        Integer goodsCount = goodsMapper.selectCount(new LambdaQueryWrapper<TGoods>()
                .eq(TGoods::getStoreId, storeId));
        homeContentVo.setGoodsCount(goodsCount);
        Integer orderCount = orderMapper.selectCount(new LambdaQueryWrapper<TOrder>()
                .eq(TOrder::getStoreId, storeId));
        homeContentVo.setOrderCount(orderCount);
        Integer waitingPayOrderCount = orderMapper.selectCount(new LambdaQueryWrapper<TOrder>()
                .eq(TOrder::getStoreId, storeId)
                .eq(TOrder::getOrderStatus, OrderStatusEnum.WAITING_PAY.getCode()));
        homeContentVo.setWaitingPayOrderCount(waitingPayOrderCount);
        Integer waitingSendOrderCount = orderMapper.selectCount(new LambdaQueryWrapper<TOrder>()
                .eq(TOrder::getStoreId, storeId)
                .eq(TOrder::getOrderStatus, OrderStatusEnum.WAITING_SEND.getCode()));
        homeContentVo.setWaitingSendOrderCount(waitingSendOrderCount);
        Integer waitingDeliveryOrderCount = orderMapper.selectCount(new LambdaQueryWrapper<TOrder>()
                .eq(TOrder::getStoreId, storeId)
                .eq(TOrder::getOrderStatus, OrderStatusEnum.WAITING_DELIVERY.getCode()));
        homeContentVo.setWaitingDeliveryOrderCount(waitingDeliveryOrderCount);
        BigDecimal totalIncome = orderMapper.totalIncome(storeId);
        homeContentVo.setTotalIncome(totalIncome);
        String format = DateUtil.format(DateUtil.yesterday(), "yyyy-MM-dd");
        BigDecimal yesterdayIncome = orderMapper.yesterdayIncome(storeId, format);
        homeContentVo.setYesterdayIncome(yesterdayIncome);
        Integer yesterdayOrderCount = orderMapper.yesterdayOrderCount(storeId, format);
        homeContentVo.setYesterdayOrderCount(yesterdayOrderCount);
        return Result.success(homeContentVo);
    }

    private Integer getStoreId() {
        String authorization = HttpUtil.getRequest().getHeader("Authorization");
        return jwtUtil.getStoreId(authorization);
    }
}
