package com.wwx.teamall.controller;


import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/order/detail")
public class TOrderDetailController {
    @Autowired
    private TOrderDetailService orderDetailService;

    @GetMapping("/list")
    public Result getList() {
        return orderDetailService.getOrderDetailList();
    }
}

