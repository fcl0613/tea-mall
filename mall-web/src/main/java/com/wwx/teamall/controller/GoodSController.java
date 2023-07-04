package com.wwx.teamall.controller;

import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodSController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public Result getGoodsList(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Long pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "20") Long pageSize) {
        return goodsService.getGoodsList(categoryId, keyword, pageNum, pageSize);
    }

    @GetMapping("/info")
    public Result getGoodsInfo(@RequestParam("id") Integer id) {
        return goodsService.getGoodsInfo(id);
    }
}
