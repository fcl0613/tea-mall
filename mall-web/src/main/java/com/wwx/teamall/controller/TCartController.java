package com.wwx.teamall.controller;


import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-03-27
 */
@RestController
@RequestMapping("/cart")
public class TCartController {
    @Autowired
    private TCartService cartService;

    @PostMapping("/add")
    public Result addCart(@RequestParam("id") Integer goodsId) {
        return cartService.addCart(goodsId);
    }

    @PostMapping("/update/count")
    public Result updateCount(@RequestParam("id") Integer id,
                              @RequestParam("count") Integer count) {
        return cartService.updateCount(id, count);
    }

    @GetMapping("/list")
    public Result getCartList() {
        return cartService.getCartList();
    }

    @PostMapping("/delete")
    public Result deleteCart(@RequestParam("id") Integer id) {
        return cartService.deleteCart(id);
    }
}

