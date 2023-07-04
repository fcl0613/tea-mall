package com.wwx.teamall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwx.teamall.entity.DO.GoodsListDO;
import com.wwx.teamall.entity.TGoods;
import com.wwx.teamall.entity.vo.GoodsInfoPageVo;
import com.wwx.teamall.entity.vo.GoodsListVo;
import com.wwx.teamall.mapper.TGoodsMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TGoodsMapper goodsMapper;

    @Override
    public Result getGoodsList(Integer categoryId, String keyword, Long pageNum, Long pageSize) {
        Page<TGoods> goodsPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TGoods> queryWrapper = new LambdaQueryWrapper<>();
        if (null != categoryId) {
            queryWrapper.eq(TGoods::getCategoryId, categoryId);
        }
        if (!StrUtil.isBlank(keyword)) {
            queryWrapper.like(TGoods::getGoodsName, keyword);
        }
        Page<TGoods> page = goodsMapper.selectPage(goodsPage, queryWrapper);
        List<TGoods> records = page.getRecords();
        ArrayList<GoodsListDO> goodsListDOS = new ArrayList<>();
        for (TGoods record : records) {
            GoodsListDO goodsListDO = new GoodsListDO();
            goodsListDO.setId(record.getId());
            goodsListDO.setGoodsCover(record.getGoodsCover());
            goodsListDO.setGoodsName(record.getGoodsName());
            goodsListDO.setPrice(record.getPrice());
            goodsListDOS.add(goodsListDO);
        }
        GoodsListVo goodsListVo = new GoodsListVo();
        goodsListVo.setList(goodsListDOS);
        goodsListVo.setTotal(page.getTotal());
        return Result.success(goodsListVo);
    }

    @Override
    public Result getGoodsInfo(Integer id) {
        TGoods goods = goodsMapper.selectById(id);
        GoodsInfoPageVo goodsInfoPageVo = new GoodsInfoPageVo();
        goodsInfoPageVo.setGoodsId(goods.getId());
        goodsInfoPageVo.setSubtitle(goods.getSubtitle());
        goodsInfoPageVo.setStock(goods.getStock());
        goodsInfoPageVo.setPrice(goods.getPrice());
        goodsInfoPageVo.setGoodsName(goods.getGoodsName());
        String goodsShowPic = goods.getGoodsShowPic();
        ArrayList<String> goodsCoverList = new ArrayList<>();
        goodsCoverList.add(goods.getGoodsCover());
        for (String s : goodsShowPic.split(",")) {
            goodsCoverList.add(s);
        }
        goodsInfoPageVo.setGoodsCoverList(goodsCoverList);
        goodsInfoPageVo.setGoodsDetailList(Arrays.asList(goods.getGoodsDetails().split(",")));
        // TODO 需要商品相关评论的信息 商品评论总数和分页5条数据
        goodsInfoPageVo.setCommentsCount(0);
        return Result.success(goodsInfoPageVo);
    }
}
