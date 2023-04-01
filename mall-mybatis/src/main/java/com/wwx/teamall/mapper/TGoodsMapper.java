package com.wwx.teamall.mapper;

import com.wwx.teamall.entity.TGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwx
 * @since 2023-03-22
 */
@Mapper
public interface TGoodsMapper extends BaseMapper<TGoods> {
    void addStock(@Param("goodsId") Integer goodsId,
                  @Param("amount") Integer amount);
}
