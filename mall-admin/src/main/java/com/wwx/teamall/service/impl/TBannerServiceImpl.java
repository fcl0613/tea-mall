package com.wwx.teamall.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.TBanner;
import com.wwx.teamall.entity.vo.BannerListVo;
import com.wwx.teamall.enums.BannerPublishEnum;
import com.wwx.teamall.mapper.TBannerMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-25
 */
@Service
public class TBannerServiceImpl extends ServiceImpl<TBannerMapper, TBanner> implements TBannerService {

    @Autowired
    private TBannerMapper bannerMapper;

    @Override
    public Result addBanner(TBanner banner) {
        this.save(banner);
        return Result.success();
    }

    @Override
    public Result delete(Integer id) {
        this.removeById(id);
        return Result.success();
    }

    @Override
    public Result publishBanner(Integer id) {
        this.update(new LambdaUpdateWrapper<TBanner>()
        .eq(TBanner::getId, id)
        .set(TBanner::getPublishStatus, BannerPublishEnum.PUBLISH.getCode()));
        return Result.success();
    }

    @Override
    public Result unPublishBanner(Integer id) {
        this.update(new LambdaUpdateWrapper<TBanner>()
                .eq(TBanner::getId, id)
                .set(TBanner::getPublishStatus, BannerPublishEnum.UN_PUBLISH.getCode()));
        return Result.success();
    }

    @Override
    public Result getBannerList(Long pageNum, Long pageSize) {
        Page<TBanner> bannerPage = new Page<>(pageNum, pageSize);
        Page<TBanner> page = bannerMapper.selectPage(bannerPage, null);
        BannerListVo bannerListVo = new BannerListVo();
        bannerListVo.setList(page.getRecords());
        bannerListVo.setTotal(page.getTotal());
        return Result.success(bannerListVo);
    }
}
