package com.wwx.teamall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.DO.OrderItemDO;
import com.wwx.teamall.entity.DTO.ConfirmOrderDTO;
import com.wwx.teamall.entity.DTO.CreateOrderDTO;
import com.wwx.teamall.entity.DTO.GetOrderListDTO;
import com.wwx.teamall.entity.TAddress;
import com.wwx.teamall.entity.TCart;
import com.wwx.teamall.entity.TGoods;
import com.wwx.teamall.entity.TOrder;
import com.wwx.teamall.entity.TOrderDetail;
import com.wwx.teamall.entity.TStore;
import com.wwx.teamall.entity.vo.OrderConfirmGoodsVo;
import com.wwx.teamall.entity.vo.OrderConfirmItemVo;
import com.wwx.teamall.entity.vo.OrderConfirmVo;
import com.wwx.teamall.entity.vo.OrderDetailVo;
import com.wwx.teamall.entity.vo.OrderListVo;
import com.wwx.teamall.enums.GoodsHasCommentEnum;
import com.wwx.teamall.enums.OrderStatusEnum;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.GoodsMapper;
import com.wwx.teamall.mapper.TAddressMapper;
import com.wwx.teamall.mapper.TCartMapper;
import com.wwx.teamall.mapper.TGoodsMapper;
import com.wwx.teamall.mapper.TOrderMapper;
import com.wwx.teamall.mapper.TStoreMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TOrderDetailService;
import com.wwx.teamall.service.TOrderService;
import com.wwx.teamall.utils.HttpUtil;
import com.wwx.teamall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-31
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private TCartMapper cartMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private TStoreMapper storeMapper;

    @Autowired
    private TAddressMapper addressMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private TOrderDetailService orderDetailService;

    @Autowired
    private TGoodsMapper tGoodsMapper;


    @Override
    public Result confirmOrder(ConfirmOrderDTO confirmOrderDTO) {
        List<TCart> carts = cartMapper.selectList(new LambdaQueryWrapper<TCart>()
                .in(TCart::getId, confirmOrderDTO.getCartIdList()));
        List<Integer> goodsIds = new ArrayList<>();
        List<Integer> cartIds = new ArrayList<>();
        for (TCart cart : carts) {
            goodsIds.add(cart.getGoodsId());
            cartIds.add(cart.getId());
        }
        // 获取购物车中的商品信息
        List<TGoods> goodsList = tGoodsMapper.selectList(new LambdaQueryWrapper<TGoods>()
                .in(TGoods::getId, goodsIds));
        List<Integer> storeIds = new ArrayList<>();
        for (TGoods goods : goodsList) {
            storeIds.add(goods.getStoreId());
        }
        // 找到相关的店铺信息
        List<TStore> stores = storeMapper.selectList(new LambdaQueryWrapper<TStore>()
                .in(TStore::getId, storeIds));
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        List<OrderConfirmItemVo> orderItemList = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (TStore store : stores) {
            OrderConfirmItemVo orderConfirmItemVo = new OrderConfirmItemVo();
            orderConfirmItemVo.setStoreId(store.getId());
            orderConfirmItemVo.setStoreName(store.getStoreName());
            List<OrderConfirmGoodsVo> list = new ArrayList<>();
            for (TGoods goods : goodsList) {
                if (store.getId().equals(goods.getStoreId())) {
                    for (TCart cart : carts) {
                        if (goods.getId().equals(cart.getGoodsId())) {
                            OrderConfirmGoodsVo orderConfirmGoodsVo = new OrderConfirmGoodsVo();
                            orderConfirmGoodsVo.setGoodsCount(cart.getGoodsCount());
                            orderConfirmGoodsVo.setGoodsCover(goods.getGoodsCover());
                            orderConfirmGoodsVo.setGoodsId(goods.getId());
                            orderConfirmGoodsVo.setGoodsName(goods.getGoodsName());
                            orderConfirmGoodsVo.setGoodsPrice(goods.getPrice());
                            totalPrice =
                                    totalPrice.add(goods.getPrice().multiply(new BigDecimal(cart.getGoodsCount())));
                            list.add(orderConfirmGoodsVo);
                        }
                    }
                }
            }
            orderConfirmItemVo.setGoodsList(list);
            orderItemList.add(orderConfirmItemVo);
        }
        List<TAddress> tAddresses = addressMapper.selectList(new LambdaQueryWrapper<TAddress>()
                .eq(TAddress::getUserId, getUserId()));
        orderConfirmVo.setAddressList(tAddresses);
        orderConfirmVo.setOrderConfirmItemList(orderItemList);
        orderConfirmVo.setTotalPrice(totalPrice);
        orderConfirmVo.setCartIds(cartIds);
        return Result.success(orderConfirmVo);
    }

    @Override
    public Result createOrder(CreateOrderDTO createOrderDTO) {
        List<TCart> carts = cartMapper.selectList(new LambdaQueryWrapper<TCart>()
                .in(TCart::getId, createOrderDTO.getCartIds()));
        List<Integer> cartIds = new ArrayList<>();
        List<Integer> goodsIds = new ArrayList<>();
        for (TCart cart : carts) {
            goodsIds.add(cart.getGoodsId());
            cartIds.add(cart.getId());
        }
        // 获取地址信息
        TAddress address = addressMapper.selectById(createOrderDTO.getAddressId());
        // 获取购物车中的商品信息
        List<TGoods> goodsList = tGoodsMapper.selectList(new LambdaQueryWrapper<TGoods>()
                .in(TGoods::getId, goodsIds));
        Set<Integer> storeIdSet = new HashSet<>();
        for (TGoods goods : goodsList) {
            storeIdSet.add(goods.getStoreId());
        }
        Integer userId = getUserId();
        LocalDateTime localDateTime = LocalDateTime.now();
        for (Integer storeId : storeIdSet) {
            TOrder order = new TOrder();
            order.setCity(address.getCity());
            order.setCounty(address.getCounty());
            order.setCreateTime(localDateTime);
            order.setDeliveryAddress(address.getAddress());
            order.setDeliveryName(address.getLinkName());
            order.setDeliveryPhone(address.getLinkPhone());
            order.setOrderStatus(OrderStatusEnum.WAITING_PAY.getCode());
            order.setProvice(address.getProvince());
            order.setStoreId(storeId);
            order.setUserId(userId);
            BigDecimal totalPrice = BigDecimal.ZERO;
            List<TOrderDetail> orderDetails = new ArrayList<>();
            for (TGoods goods : goodsList) {
                if (storeId.equals(goods.getStoreId())) {
                    for (TCart cart : carts) {
                        if (goods.getId().equals(cart.getGoodsId())) {
                            TOrderDetail orderDetail = new TOrderDetail();
                            orderDetail.setCreateTime(localDateTime);
                            orderDetail.setGoodsCount(cart.getGoodsCount());
                            orderDetail.setGoodsCover(goods.getGoodsCover());
                            orderDetail.setGoodsId(goods.getId());
                            orderDetail.setGoodsName(goods.getGoodsName());
                            orderDetail.setGoodsPrice(goods.getPrice());
                            orderDetail.setHasCommented(GoodsHasCommentEnum.ORDER_UN_FINISH.getCode());
                            orderDetail.setUserId(userId);
                            orderDetails.add(orderDetail);
                            totalPrice =
                                    totalPrice.add(goods.getPrice().multiply(new BigDecimal(cart.getGoodsCount())));
                            // 扣减库存
                            if (goods.getStock() < cart.getGoodsCount()) {
                                throw new BadRequestException("商品" + goods.getGoodsName() + "库存不足");
                            }
                            goods.setStock(goods.getStock() - cart.getGoodsCount());
                        }
                    }
                }
            }
            order.setTotalPrice(totalPrice);
            // 加入订单表
            this.save(order);
            for (TOrderDetail orderDetail : orderDetails) {
                orderDetail.setOrderId(order.getId());
            }
            // 加入订单详情表
            orderDetailService.saveBatch(orderDetails);
        }
        // 更新库存
        for (TGoods goods : goodsList) {
            tGoodsMapper.update(null, new LambdaUpdateWrapper<TGoods>()
            .eq(TGoods::getId, goods.getId())
            .set(TGoods::getStock, goods.getStock()));
        }
        // 删除购物车中的信息
        cartMapper.deleteBatchIds(cartIds);
        return Result.success();
    }

    @Override
    public Result directConfirm(Integer id, Integer count) {
        // 获取商品信息
        TGoods goods = tGoodsMapper.selectById(id);
        Integer userId = getUserId();
        // 获取收货地址相关信息
        List<TAddress> addresses = addressMapper.selectList(new LambdaQueryWrapper<TAddress>()
                .eq(TAddress::getUserId, userId));
        // 获取商店信息
        TStore store = storeMapper.selectById(goods.getStoreId());
        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice = totalPrice.add(goods.getPrice().multiply(new BigDecimal(count)));
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        orderConfirmVo.setTotalPrice(totalPrice);
        orderConfirmVo.setAddressList(addresses);
        List<OrderConfirmItemVo> orderConfirmItemVos = new ArrayList<>();
        OrderConfirmItemVo orderConfirmItemVo = new OrderConfirmItemVo();
        orderConfirmItemVo.setStoreName(store.getStoreName());
        orderConfirmItemVo.setStoreId(store.getId());
        List<OrderConfirmGoodsVo> goodsVos = new ArrayList<>();
        OrderConfirmGoodsVo orderConfirmGoodsVo = new OrderConfirmGoodsVo();
        orderConfirmGoodsVo.setGoodsPrice(goods.getPrice());
        orderConfirmGoodsVo.setGoodsName(goods.getGoodsName());
        orderConfirmGoodsVo.setGoodsId(goods.getId());
        orderConfirmGoodsVo.setGoodsCover(goods.getGoodsCover());
        orderConfirmGoodsVo.setGoodsCount(count);
        goodsVos.add(orderConfirmGoodsVo);
        orderConfirmItemVo.setGoodsList(goodsVos);
        orderConfirmItemVos.add(orderConfirmItemVo);
        orderConfirmVo.setOrderConfirmItemList(orderConfirmItemVos);
        return Result.success(orderConfirmVo);
    }

    @Override
    public Result directBuy(Integer id, Integer count, Integer addressId) {
        // 找商品信息
        TGoods goods = tGoodsMapper.selectById(id);
        Integer stock = goods.getStock();
        if (stock - count < 0) {
            throw new BadRequestException("当前商品库存不足");
        }
        // 获取地址信息
        TAddress address = addressMapper.selectById(addressId);
        Integer userId = getUserId();
        TOrder order = new TOrder();
        order.setTotalPrice(goods.getPrice().multiply(new BigDecimal(count)));
        order.setUserId(userId);
        order.setStoreId(goods.getStoreId());
        order.setProvice(address.getProvince());
        order.setOrderStatus(OrderStatusEnum.WAITING_PAY.getCode());
        order.setDeliveryPhone(address.getLinkPhone());
        order.setDeliveryName(address.getLinkName());
        order.setDeliveryAddress(address.getAddress());
        LocalDateTime createTime = LocalDateTime.now();
        order.setCreateTime(createTime);
        order.setCounty(address.getCounty());
        order.setCity(address.getCity());
        // 加入订单表
        this.save(order);
        TOrderDetail orderDetail = new TOrderDetail();
        orderDetail.setOrderId(order.getId());
        orderDetail.setUserId(userId);
        orderDetail.setHasCommented(GoodsHasCommentEnum.ORDER_UN_FINISH.getCode());
        orderDetail.setGoodsPrice(goods.getPrice());
        orderDetail.setGoodsName(goods.getGoodsName());
        orderDetail.setGoodsId(goods.getId());
        orderDetail.setGoodsCover(goods.getGoodsCover());
        orderDetail.setGoodsCount(count);
        orderDetail.setCreateTime(createTime);
        orderDetailService.save(orderDetail);
        // 扣减库存
        tGoodsMapper.update(null, new LambdaUpdateWrapper<TGoods>()
        .eq(TGoods::getId, goods.getId())
        .set(TGoods::getStock, goods.getStock() - count));
        return Result.success();
    }

    @Override
    public Result getOrderList(GetOrderListDTO dto) {
        OrderListVo orderListVo = new OrderListVo();
        Page<TOrder> orderPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        Integer userId = getUserId();
        LambdaQueryWrapper<TOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TOrder::getUserId, userId);
        if (OrderStatusEnum.checkCode(dto.getStatus())) {
            queryWrapper.eq(TOrder::getOrderStatus, dto.getStatus());
        }
        queryWrapper.orderByDesc(TOrder::getCreateTime);
        Page<TOrder> page = this.page(orderPage, queryWrapper);
        List<TOrder> records = page.getRecords();
        if (records.size() == 0) {
            orderListVo.setOrderList(new ArrayList<>());
            orderListVo.setTotal(0l);
            return Result.success(orderListVo);
        }
        List<String> orderIds = new ArrayList<>();
        for (TOrder record : records) {
            orderIds.add(record.getId());
        }
        // 找到订单详情
        List<TOrderDetail> orderDetails = orderDetailService.list(new LambdaQueryWrapper<TOrderDetail>()
                .in(TOrderDetail::getOrderId, orderIds));
        List<OrderItemDO> orderItemDOS = new ArrayList<>();
        for (TOrder record : records) {
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setCreateTime(record.getCreateTime());
            orderItemDO.setDeliveryName(record.getDeliveryName());
            orderItemDO.setOrderId(record.getId());
            orderItemDO.setStatus(record.getOrderStatus());
            orderItemDO.setStatusStr(OrderStatusEnum.findDescription(record.getOrderStatus()));
            orderItemDO.setTotalPrice(record.getTotalPrice());
            List<TOrderDetail> list = new ArrayList<>();
            for (TOrderDetail orderDetail : orderDetails) {
                if (record.getId().equals(orderDetail.getOrderId())) {
                    list.add(orderDetail);
                }
            }
            orderItemDO.setGoodsList(list);
            orderItemDOS.add(orderItemDO);
        }
        orderListVo.setOrderList(orderItemDOS);
        orderListVo.setTotal(page.getTotal());
        return Result.success(orderListVo);
    }

    @Override
    public Result orderPay(String id) {
        this.update(null, new LambdaUpdateWrapper<TOrder>()
        .eq(TOrder::getId, id)
        .set(TOrder::getOrderStatus, OrderStatusEnum.WAITING_SEND.getCode()));
        return Result.success();
    }

    @Override
    public Result orderCancel(String id) {
        this.update(null, new LambdaUpdateWrapper<TOrder>()
                .eq(TOrder::getId, id)
                .set(TOrder::getOrderStatus, OrderStatusEnum.CANCEL.getCode()));
        // 增加库存
        List<TOrderDetail> list = orderDetailService.list(new LambdaQueryWrapper<TOrderDetail>()
                .eq(TOrderDetail::getOrderId, id));
        for (TOrderDetail orderDetail : list) {
            goodsMapper.addStock(orderDetail.getGoodsId(), orderDetail.getGoodsCount());
        }
        return Result.success();
    }

    @Override
    public Result orderConfirm(String id) {
        this.update(null, new LambdaUpdateWrapper<TOrder>()
                .eq(TOrder::getId, id)
                .set(TOrder::getOrderStatus, OrderStatusEnum.FINISHED.getCode()));
        orderDetailService.update(null, new LambdaUpdateWrapper<TOrderDetail>()
        .eq(TOrderDetail::getOrderId, id)
        .set(TOrderDetail::getHasCommented, GoodsHasCommentEnum.NO_COMMENTED.getCode()));
        return Result.success();
    }

    @Override
    public Result getOrderDetail(String id) {
        TOrder order = this.getById(id);
        List<TOrderDetail> list = orderDetailService.list(new LambdaQueryWrapper<TOrderDetail>()
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

    private Integer getUserId() {
        HttpServletRequest request = HttpUtil.getRequest();
        String authorization = request.getHeader("Authorization");
        return jwtUtil.getUserId(authorization);
    }
}
