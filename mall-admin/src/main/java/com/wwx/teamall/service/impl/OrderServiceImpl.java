package com.wwx.teamall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.DO.OrderListDO;
import com.wwx.teamall.entity.DTO.GetOrderListDTO;
import com.wwx.teamall.entity.TOrder;
import com.wwx.teamall.entity.TOrderDetail;
import com.wwx.teamall.entity.vo.OrderDetailVo;
import com.wwx.teamall.entity.vo.OrderListVo;
import com.wwx.teamall.enums.OrderStatusEnum;
import com.wwx.teamall.mapper.TGoodsMapper;
import com.wwx.teamall.mapper.TOrderDetailMapper;
import com.wwx.teamall.mapper.TOrderMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements OrderService {

    private final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private TOrderMapper orderMapper;

    @Autowired
    private TOrderDetailMapper orderDetailMapper;

    @Autowired
    private TGoodsMapper goodsMapper;


    @Override
    public Result getOrderList(GetOrderListDTO dto) {
        Page<TOrder> orderPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<TOrder> queryWrapper = new LambdaQueryWrapper<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_TIME);
        if (!StrUtil.isBlank(dto.getOrderId())) {
            queryWrapper.eq(TOrder::getId, dto.getOrderId());
        }
        if (!Objects.isNull(dto.getOrderStatus())) {
            queryWrapper.eq(TOrder::getOrderStatus, dto.getOrderStatus());
        }
        if (!StrUtil.isBlank(dto.getBeginTime())) {
            queryWrapper.ge(TOrder::getCreateTime, LocalDateTime.parse(dto.getBeginTime(), dateTimeFormatter));
        }
        if (!StrUtil.isBlank(dto.getEndTime())) {
            queryWrapper.le(TOrder::getCreateTime, LocalDateTime.parse(dto.getEndTime(), dateTimeFormatter));
        }
        Page<TOrder> page = orderMapper.selectPage(orderPage, queryWrapper);
        List<OrderListDO> list = new ArrayList<>();
        for (TOrder record : page.getRecords()) {
            OrderListDO orderListDO = new OrderListDO();
            orderListDO.setCreatTime(record.getCreateTime());
            orderListDO.setOrderId(record.getId());
            orderListDO.setOrderStatus(record.getOrderStatus());
            orderListDO.setOrderStatusStr(OrderStatusEnum.findDescription(record.getOrderStatus()));
            orderListDO.setTotalPrice(record.getTotalPrice());
            list.add(orderListDO);
        }
        OrderListVo orderListVo = new OrderListVo();
        orderListVo.setTotal(page.getTotal());
        orderListVo.setList(list);
        return Result.success(orderListVo);
    }

    @Override
    public Result getOrderDetail(String id) {
        TOrder order = this.getById(id);
        List<TOrderDetail> list = orderDetailMapper.selectList(new LambdaQueryWrapper<TOrderDetail>()
                .eq(TOrderDetail::getOrderId, id));
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setAddress(order.getProvice()+order.getCity()+order.getCounty()+order.getDeliveryAddress());
        orderDetailVo.setDeliveryName(order.getDeliveryName());
        orderDetailVo.setDeliveryPhone(order.getDeliveryPhone());
        orderDetailVo.setOrderId(order.getId());
        orderDetailVo.setStatus(order.getOrderStatus());
        orderDetailVo.setStatusStr(OrderStatusEnum.findDescription(order.getOrderStatus()));
        orderDetailVo.setTotalPrice(order.getTotalPrice());
        orderDetailVo.setGoods(list);
        return Result.success(orderDetailVo);
    }

    @Override
    public Result cancelOrder(String id) {
        this.update(null, new LambdaUpdateWrapper<TOrder>()
                .eq(TOrder::getId, id)
                .set(TOrder::getOrderStatus, OrderStatusEnum.CANCEL.getCode()));
        // 增加库存
        List<TOrderDetail> list = orderDetailMapper.selectList(new LambdaQueryWrapper<TOrderDetail>()
                .eq(TOrderDetail::getOrderId, id));
        for (TOrderDetail orderDetail : list) {
            goodsMapper.addStock(orderDetail.getGoodsId(), orderDetail.getGoodsCount());
        }
        return Result.success();
    }

    @Override
    public Result deleteOrder(String id) {
        this.removeById(id);
        return Result.success();
    }
}
