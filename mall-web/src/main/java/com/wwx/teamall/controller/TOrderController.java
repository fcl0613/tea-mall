package com.wwx.teamall.controller;


import com.wwx.teamall.entity.DTO.ConfirmOrderDTO;
import com.wwx.teamall.entity.DTO.CreateOrderDTO;
import com.wwx.teamall.entity.DTO.GetOrderListDTO;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;
    
    @PostMapping("/confirm")
    public Result confirmOrder(@RequestBody ConfirmOrderDTO confirmOrderDTO) {
        return orderService.confirmOrder(confirmOrderDTO);
    }

    @PostMapping("/create")
    public Result createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO);
    }

    @PostMapping("/direct/confirm")
    public Result directConfirm(@RequestParam("id") Integer id,
                            @RequestParam("count") Integer count) {
        return orderService.directConfirm(id, count);
    }

    @PostMapping("/direct/buy")
    public Result directBuy(@RequestParam("goodsId") Integer goodsId,
                            @RequestParam("count") Integer count,
                            @RequestParam("addressId") Integer addressId) {
        return orderService.directBuy(goodsId, count, addressId);
    }

    @PostMapping("/list")
    public Result getOrderList(@RequestBody GetOrderListDTO dto) {
        return orderService.getOrderList(dto);
    }

    @PostMapping("/pay")
    public Result orderPay(@RequestParam("id") String id) {
        return orderService.orderPay(id);
    }

    @PostMapping("/cancel")
    public Result orderCancel(@RequestParam("id") String id) {
        return orderService.orderCancel(id);
    }

    @PostMapping("/determine")
    public Result orderConfirm(@RequestParam("id") String id) {
        return orderService.orderConfirm(id);
    }

    @GetMapping("/detail")
    public Result getOrderDetail(@RequestParam("id") String id) {
        return orderService.getOrderDetail(id);
    }
}

