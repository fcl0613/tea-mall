package com.wwx.teamall.controller;


import com.wwx.teamall.entity.DTO.AddGoodsDTO;
import com.wwx.teamall.entity.DTO.GetGoodsListDTO;
import com.wwx.teamall.entity.DTO.UpdateGoodsDTO;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-03-22
 */
@RestController
@RequestMapping("/goods")
public class TGoodsController {

    @Autowired
    private TGoodsService goodsService;

    @PostMapping("/list")
    public Result getGoodsList(@RequestBody GetGoodsListDTO getGoodsListDTO) {
       return goodsService.getGoodsList(getGoodsListDTO);
    }

    @PostMapping("/add")
    public Result addGoods(@RequestBody AddGoodsDTO addGoodsDTO) {
        return goodsService.addGoods(addGoodsDTO);
    }

    @PostMapping("/update")
    public Result updateGoods(@RequestBody UpdateGoodsDTO updateGoodsDTO) {
        return goodsService.updateGoods(updateGoodsDTO);
    }

    @RequestMapping("/info")
    public Result getGoodsInfo(@RequestParam("id") Integer id) {
        return goodsService.getGoodsInfo(id);
    }

    @PostMapping("/delete")
    public Result deleteGoodsInfo(@RequestParam("id") Integer id) {
        return goodsService.deleteGoods(id);
    }

    @PostMapping("/update/stock")
    public Result updateStock(@RequestParam("id") Integer id,
                              @RequestParam("stock") Integer stock) {
        return goodsService.updateStock(id, stock);
    }

    @PostMapping("/update/status")
    public Result updateStatus(@RequestParam("id") Integer id,
                              @RequestParam("status") Integer status) {
        return goodsService.updateStatus(id, status);
    }
}

