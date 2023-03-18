package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.TCategory;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-18
 */
public interface TCategoryService extends IService<TCategory> {

    Result create(TCategory category);

    Result getList(String keyword, Long pageNum, Long pageSize);

    Result updateCategory(TCategory category);

    Result delete(Integer id);

    Result getInfo(Integer id);
}
