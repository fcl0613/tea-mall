package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-18
 */
public interface TUserService extends IService<TUser> {

    Result create(TUser user);

    Result getList(String keyword, Long pageNum, Long pageSize);

    Result updateUser(TUser user);

    Result delete(Integer id);

    Result getInfo(Integer id);
}
