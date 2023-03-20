package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.DTO.GetApplyListDTO;
import com.wwx.teamall.entity.DTO.RefuseApplyDTO;
import com.wwx.teamall.entity.TStoreApply;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-03-20
 */
public interface TStoreApplyService extends IService<TStoreApply> {

    Result getList(GetApplyListDTO dto);

    Result agreeApply(Integer id);

    Result refuseApply(RefuseApplyDTO refuseApplyDTO);
}
