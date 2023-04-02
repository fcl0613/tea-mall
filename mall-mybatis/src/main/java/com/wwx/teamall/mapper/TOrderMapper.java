package com.wwx.teamall.mapper;

import com.wwx.teamall.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwx
 * @since 2023-03-31
 */
@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {
    BigDecimal totalIncome(@Param("storeId") Integer storeId);

    BigDecimal yesterdayIncome(@Param("storeId") Integer storeId,
                               @Param("date") String date);

    Integer yesterdayOrderCount(@Param("storeId") Integer storeId,
                                @Param("date") String date);
}
