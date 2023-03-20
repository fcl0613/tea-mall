package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.TStore;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-20
 */
public interface TStoreService extends IService<TStore> {

    Result getList(String keyword, Long pageNum, Long pageSize);
}
