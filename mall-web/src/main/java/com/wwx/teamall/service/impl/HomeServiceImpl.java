package com.wwx.teamall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwx.teamall.entity.TBanner;
import com.wwx.teamall.entity.TCategory;
import com.wwx.teamall.entity.TGoods;
import com.wwx.teamall.entity.vo.HomeCategoryVo;
import com.wwx.teamall.entity.vo.HomeContentVo;
import com.wwx.teamall.entity.vo.HomeGoodsVo;
import com.wwx.teamall.enums.BannerPublishEnum;
import com.wwx.teamall.mapper.TBannerMapper;
import com.wwx.teamall.mapper.TCategoryMapper;
import com.wwx.teamall.mapper.TGoodsMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private TCategoryMapper categoryMapper;

    @Autowired
    private TGoodsMapper goodsMapper;

    @Autowired
    private TBannerMapper bannerMapper;

    @Override
    public Result getContent() {
        HomeContentVo homeContentVo = new HomeContentVo();
        List<TBanner> banners = bannerMapper.selectList(new LambdaQueryWrapper<TBanner>()
                .eq(TBanner::getPublishStatus, BannerPublishEnum.PUBLISH.getCode()));
        List<TCategory> categoryList = categoryMapper.selectList(null);
        ArrayList<HomeCategoryVo> homeCategoryVos = new ArrayList<>();
        for (TCategory category : categoryList) {
            if (category.getParentId() == 0) {
                HomeCategoryVo homeCategoryVo1 = new HomeCategoryVo();
                homeCategoryVo1.setId(category.getId());
                homeCategoryVo1.setCategoryName(category.getCategoryName());
                ArrayList<HomeCategoryVo> homeCategoryVos1 = new ArrayList<>();
                for (TCategory tCategory : categoryList) {
                    if (tCategory.getParentId() == category.getId()) {
                        HomeCategoryVo homeCategoryVo = new HomeCategoryVo();
                        homeCategoryVo.setId(tCategory.getId());
                        homeCategoryVo.setCategoryName(tCategory.getCategoryName());
                        homeCategoryVos1.add(homeCategoryVo);
                    }
                }
                homeCategoryVo1.setChildren(homeCategoryVos1);
                // 找商品
                List<TGoods> goodsList = goodsMapper.selectList(new LambdaQueryWrapper<TGoods>()
                        .eq(TGoods::getCategoryParentId, category.getId())
                        .last("limit 8"));
                ArrayList<HomeGoodsVo> homeGoodsVos = new ArrayList<>();
                for (TGoods goods : goodsList) {
                    HomeGoodsVo homeGoodsVo = new HomeGoodsVo();
                    homeGoodsVo.setId(goods.getId());
                    homeGoodsVo.setGoodsCover(goods.getGoodsCover());
                    homeGoodsVo.setGoodsName(goods.getGoodsName());
                    homeGoodsVo.setPrice(goods.getPrice());
                    homeGoodsVos.add(homeGoodsVo);
                }
                homeCategoryVo1.setGoodsList(homeGoodsVos);
                homeCategoryVos.add(homeCategoryVo1);
            }
        }
        homeContentVo.setBannerList(banners);
        homeContentVo.setCategoryList(homeCategoryVos);
        return Result.success(homeContentVo);
    }
}
