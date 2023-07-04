package com.wwx.teamall.controller;

import com.wwx.teamall.entity.DTO.GetOrderListDTO;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/list")
    public Result getOrderList(@RequestBody GetOrderListDTO dto) {
        return orderService.getOrderList(dto);
    }

    @GetMapping("/detail")
    public Result getOrderDetail(@RequestParam("id") String id) {
        return orderService.getOrderDetail(id);
    }

    @PostMapping("/cancel")
    public Result cancelOrder(@RequestParam("id") String id) {
        return orderService.cancelOrder(id);
    }

    @PostMapping("/delete")
    public Result deleteOrder(@RequestParam("id") String id) {
        return orderService.deleteOrder(id);
    }
}
