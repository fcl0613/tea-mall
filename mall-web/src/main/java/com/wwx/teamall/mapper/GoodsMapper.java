package com.wwx.teamall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsMapper {
    void addStock(@Param("goodsId") Integer goodsId,
                  @Param("amount") Integer amount);
}
