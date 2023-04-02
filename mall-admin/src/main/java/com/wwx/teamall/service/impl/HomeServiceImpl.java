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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private TGoodsMapper goodsMapper;

    @Autowired
    private TOrderMapper orderMapper;


    @Override
    public Result getHomeContent() {
        HomeContentVo homeContentVo = new HomeContentVo();
        Integer publishGoodsCount = goodsMapper.selectCount(new LambdaQueryWrapper<TGoods>()
                .eq(TGoods::getGoodsStatus, 1));
        homeContentVo.setPublishGoodsCount(publishGoodsCount);
        Integer goodsCount = goodsMapper.selectCount(null);
        homeContentVo.setGoodsCount(goodsCount);
        Integer orderCount = orderMapper.selectCount(null);
        homeContentVo.setOrderCount(orderCount);
        Integer waitingPayOrderCount = orderMapper.selectCount(new LambdaQueryWrapper<TOrder>()
                .eq(TOrder::getOrderStatus, OrderStatusEnum.WAITING_PAY.getCode()));
        homeContentVo.setWaitingPayOrderCount(waitingPayOrderCount);
        Integer waitingSendOrderCount = orderMapper.selectCount(new LambdaQueryWrapper<TOrder>()
                .eq(TOrder::getOrderStatus, OrderStatusEnum.WAITING_SEND.getCode()));
        homeContentVo.setWaitingSendOrderCount(waitingSendOrderCount);
        Integer waitingDeliveryOrderCount = orderMapper.selectCount(new LambdaQueryWrapper<TOrder>()
                .eq(TOrder::getOrderStatus, OrderStatusEnum.WAITING_DELIVERY.getCode()));
        homeContentVo.setWaitingDeliveryOrderCount(waitingDeliveryOrderCount);
        BigDecimal totalIncome = orderMapper.totalIncomeForAdmin();
        homeContentVo.setTotalIncome(totalIncome);
        String format = DateUtil.format(DateUtil.yesterday(), "yyyy-MM-dd");
        BigDecimal yesterdayIncome = orderMapper.yesterdayIncomeForAdmin(format);
        homeContentVo.setYesterdayIncome(yesterdayIncome);
        Integer yesterdayOrderCount = orderMapper.yesterdayOrderCountForAdmin(format);
        homeContentVo.setYesterdayOrderCount(yesterdayOrderCount);
        return Result.success(homeContentVo);
    }
}
