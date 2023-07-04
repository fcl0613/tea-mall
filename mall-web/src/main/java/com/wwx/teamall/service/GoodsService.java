package com.wwx.teamall.service;

import com.wwx.teamall.model.Result;

public interface GoodsService {
    Result getGoodsList(Integer categoryId, String keyword, Long pageNum, Long pageSize);

    Result getGoodsInfo(Integer id);
}
