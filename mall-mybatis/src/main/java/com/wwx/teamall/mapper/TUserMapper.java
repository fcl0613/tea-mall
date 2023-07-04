package com.wwx.teamall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwx.teamall.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwx
 * @since 2023-03-18
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    Page<TUser> getUserPage(@Param("page") Page page,
                            @Param("keyword") String keyword);
}
