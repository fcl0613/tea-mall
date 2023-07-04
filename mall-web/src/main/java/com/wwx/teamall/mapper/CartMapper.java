package com.wwx.teamall.mapper;

import com.wwx.teamall.entity.DO.CartDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartDO> getCartList(@Param("userId") Integer userId);
}
