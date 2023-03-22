package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.DTO.AddGoodsDTO;
import com.wwx.teamall.entity.DTO.GetGoodsListDTO;
import com.wwx.teamall.entity.DTO.UpdateGoodsDTO;
import com.wwx.teamall.entity.TGoods;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-22
 */
public interface TGoodsService extends IService<TGoods> {

    Result getGoodsList(GetGoodsListDTO getGoodsListDTO);

    Result addGoods(AddGoodsDTO addGoodsDTO);

    Result updateGoods(UpdateGoodsDTO updateGoodsDTO);

    Result getGoodsInfo(Integer id);

    Result deleteGoods(Integer id);

    Result updateStock(Integer id, Integer stock);

    Result updateStatus(Integer id, Integer status);
}
