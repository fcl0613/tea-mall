package com.wwx.teamall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.DTO.AddGoodsDTO;
import com.wwx.teamall.entity.DTO.GetGoodsListDTO;
import com.wwx.teamall.entity.DTO.UpdateGoodsDTO;
import com.wwx.teamall.entity.TGoods;
import com.wwx.teamall.entity.vo.GoodsInfoVo;
import com.wwx.teamall.entity.vo.GoodsListVo;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.TGoodsMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TGoodsService;
import com.wwx.teamall.utils.HttpUtil;
import com.wwx.teamall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-22
 */
@Service
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements TGoodsService {

    @Autowired
    private TGoodsMapper goodsMapper;

    @Autowired
    private JWTUtil jwtUtil;


    @Override
    public Result getGoodsList(GetGoodsListDTO getGoodsListDTO) {
        Integer storeId = getStoreId();
        Page<TGoods> goodsPage = new Page<>(getGoodsListDTO.getPageNum(), getGoodsListDTO.getPageSize());
        LambdaQueryWrapper<TGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TGoods::getStoreId, storeId);
        if (!StrUtil.isBlank(getGoodsListDTO.getGoodsName())) {
            queryWrapper.like(TGoods::getGoodsName, getGoodsListDTO.getGoodsName());
        }
        if (null != getGoodsListDTO.getCategoryId()) {
            queryWrapper.eq(TGoods::getCategoryId, getGoodsListDTO.getCategoryId());
        }
        if (null != getGoodsListDTO.getGoodsStatus()) {
            queryWrapper.eq(TGoods::getGoodsStatus, getGoodsListDTO.getGoodsStatus());
        }
        Page<TGoods> page = goodsMapper.selectPage(goodsPage, queryWrapper);
        GoodsListVo goodsListVo = new GoodsListVo();
        goodsListVo.setList(page.getRecords());
        goodsListVo.setTotal(page.getTotal());
        return Result.success(goodsListVo);
    }

    @Override
    public Result addGoods(AddGoodsDTO addGoodsDTO) {
        Integer storeId = getStoreId();
        TGoods goods = new TGoods();
        goods.setCategoryId(addGoodsDTO.getCategoryId());
        goods.setCategoryParentId(addGoodsDTO.getCategoryParentId());
        goods.setGoodsCover(addGoodsDTO.getGoodsCover());
        goods.setGoodsName(addGoodsDTO.getGoodsName());
        List<String> goodsShowPic = addGoodsDTO.getGoodsShowPic();
        String picStr = "";
        if (null != goodsShowPic) {
            for (String s : goodsShowPic) {
                picStr += s;
                picStr += ",";
            }
        }
        goods.setGoodsShowPic(picStr);
        goods.setGoodsStatus(addGoodsDTO.getGoodsStatus());
        goods.setPrice(addGoodsDTO.getPrice());
        goods.setStock(addGoodsDTO.getStock());
        goods.setStoreId(storeId);
        goods.setSubtitle(addGoodsDTO.getSubtitle());
        List<String> goodsDetails = addGoodsDTO.getGoodsDetails();
        picStr = "";
        if (null != goodsDetails) {
            for (String s : goodsDetails) {
                picStr += s;
                picStr += ",";
            }
        }
        goods.setGoodsDetails(picStr);
        goodsMapper.insert(goods);
        return Result.success();
    }

    @Override
    public Result updateGoods(UpdateGoodsDTO updateGoodsDTO) {
        Integer storeId = getStoreId();
        TGoods goods = new TGoods();
        goods.setId(updateGoodsDTO.getId());
        goods.setCategoryId(updateGoodsDTO.getCategoryId());
        goods.setCategoryParentId(updateGoodsDTO.getCategoryParentId());
        goods.setGoodsCover(updateGoodsDTO.getGoodsCover());
        goods.setGoodsName(updateGoodsDTO.getGoodsName());
        List<String> goodsShowPic = updateGoodsDTO.getGoodsShowPic();
        String picStr = "";
        if (null != goodsShowPic) {
            for (String s : goodsShowPic) {
                picStr += s;
                picStr += ",";
            }
        }
        goods.setGoodsShowPic(picStr);
        goods.setGoodsStatus(updateGoodsDTO.getGoodsStatus());
        goods.setPrice(updateGoodsDTO.getPrice());
        goods.setStock(updateGoodsDTO.getStock());
        goods.setStoreId(storeId);
        goods.setSubtitle(updateGoodsDTO.getSubtitle());
        List<String> goodsDetails = updateGoodsDTO.getGoodsDetails();
        picStr = "";
        if (null != goodsDetails) {
            for (String s : goodsDetails) {
                picStr += s;
                picStr += ",";
            }
        }
        goods.setGoodsDetails(picStr);
        goodsMapper.updateById(goods);
        return Result.success();
    }

    @Override
    public Result getGoodsInfo(Integer id) {
        TGoods goods = goodsMapper.selectById(id);
        if(Objects.isNull(goods)) {
            throw new BadRequestException("商品不存在");
        }
        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
        goodsInfoVo.setCategoryId(goods.getCategoryId());
        goodsInfoVo.setCategoryParentId(goods.getCategoryParentId());
        goodsInfoVo.setGoodsCover(goods.getGoodsCover());
        String goodsDetails = goods.getGoodsDetails();
        String[] split = goodsDetails.split(",");
        goodsInfoVo.setGoodsDetails(Arrays.asList(split));
        goodsInfoVo.setGoodsName(goods.getGoodsName());
        String[] split1 = goods.getGoodsShowPic().split(",");
        goodsInfoVo.setGoodsShowPic(Arrays.asList(split1));
        goodsInfoVo.setGoodsStatus(goods.getGoodsStatus());
        goodsInfoVo.setId(goods.getId());
        goodsInfoVo.setPrice(goods.getPrice());
        goodsInfoVo.setStock(goods.getStock());
        goodsInfoVo.setStoreId(goods.getStoreId());
        goodsInfoVo.setSubtitle(goods.getSubtitle());
        return Result.success(goodsInfoVo);
    }

    @Override
    public Result deleteGoods(Integer id) {
        goodsMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result updateStock(Integer id, Integer stock) {
        goodsMapper.update(null, new LambdaUpdateWrapper<TGoods>()
        .eq(TGoods::getId, id)
        .set(TGoods::getStock, stock));
        return Result.success();
    }

    @Override
    public Result updateStatus(Integer id, Integer status) {
        goodsMapper.update(null, new LambdaUpdateWrapper<TGoods>()
        .eq(TGoods::getId, id)
        .set(TGoods::getGoodsStatus, status));
        return Result.success();
    }

    private Integer getStoreId() {
        String authorization = HttpUtil.getRequest().getHeader("Authorization");
        return jwtUtil.getStoreId(authorization);
    }
}
