package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.TBanner;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-25
 */
public interface TBannerService extends IService<TBanner> {

    Result addBanner(TBanner banner);

    Result delete(Integer id);

    Result publishBanner(Integer id);

    Result unPublishBanner(Integer id);

    Result getBannerList(Long pageNum, Long pageSize);
}
