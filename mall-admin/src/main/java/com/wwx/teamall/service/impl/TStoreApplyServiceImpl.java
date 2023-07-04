package com.wwx.teamall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.DTO.GetApplyListDTO;
import com.wwx.teamall.entity.DTO.RefuseApplyDTO;
import com.wwx.teamall.entity.TStore;
import com.wwx.teamall.entity.TStoreApply;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.entity.vo.StoreApplyListVo;
import com.wwx.teamall.enums.StoreApplyEnum;
import com.wwx.teamall.enums.UserRoleEnum;
import com.wwx.teamall.mapper.TStoreApplyMapper;
import com.wwx.teamall.mapper.TStoreMapper;
import com.wwx.teamall.mapper.TUserMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TStoreApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-20
 */
@Service
public class TStoreApplyServiceImpl extends ServiceImpl<TStoreApplyMapper, TStoreApply> implements TStoreApplyService {

    @Autowired
    private TStoreApplyMapper storeApplyMapper;

    @Autowired
    private TStoreMapper storeMapper;

    @Autowired
    private TUserMapper userMapper;

    @Override
    public Result getList(GetApplyListDTO dto) {
        Page<TStoreApply> tStoreApplyPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<TStoreApply> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(dto.getName())) {
            queryWrapper.like("name", dto.getName());
        }
        if (!StrUtil.isBlank(dto.getPhone())) {
            queryWrapper.eq("phone", dto.getPhone());
        }
        if (!StrUtil.isBlank(dto.getStoreName())) {
            queryWrapper.like("store_name", dto.getStoreName());
        }
        if (null != dto.getStatus()) {
            queryWrapper.eq("status", dto.getStatus());
        }
        queryWrapper.orderByDesc("create_time");
        Page<TStoreApply> page = storeApplyMapper.selectPage(tStoreApplyPage, queryWrapper);
        StoreApplyListVo storeApplyListVo = new StoreApplyListVo();
        storeApplyListVo.setList(page.getRecords());
        storeApplyListVo.setTotal(page.getTotal());
        return Result.success(storeApplyListVo);
    }

    @Override
    public Result agreeApply(Integer id) {
        // 修改状态 插入商铺表 修改用户角色
        storeApplyMapper.update(null, new LambdaUpdateWrapper<TStoreApply>()
        .eq(TStoreApply::getId, id)
        .set(TStoreApply::getStatus, StoreApplyEnum.AGREE.getCode()));
        TStoreApply storeApply = storeApplyMapper.selectById(id);
        TStore store = new TStore();
        store.setStoreName(storeApply.getStoreName());
        store.setPhone(storeApply.getPhone());
        store.setUserId(storeApply.getUserId());
        store.setName(storeApply.getName());
        storeMapper.insert(store);
        userMapper.update(null, new LambdaUpdateWrapper<TUser>()
        .eq(TUser::getId, storeApply.getUserId())
        .set(TUser::getRole, UserRoleEnum.TEA_GROWER.getCode()));
        // TODO 发送短信提示用户店铺申请成功
        return Result.success();
    }

    @Override
    public Result refuseApply(RefuseApplyDTO refuseApplyDTO) {
        // 只要修改订单状态即可
        storeApplyMapper.update(null, new LambdaUpdateWrapper<TStoreApply>()
                .eq(TStoreApply::getId, refuseApplyDTO.getId())
                .set(TStoreApply::getStatus, StoreApplyEnum.REFUSE.getCode())
                .set(TStoreApply::getNote, refuseApplyDTO.getNote()));
        // TODO 发送短信提示用户店铺申请失败 有失败原因
        return Result.success();
    }
}
