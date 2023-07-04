package com.wwx.teamall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.TStore;
import com.wwx.teamall.entity.vo.StoreListVo;
import com.wwx.teamall.mapper.TStoreMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TStoreService;
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
public class TStoreServiceImpl extends ServiceImpl<TStoreMapper, TStore> implements TStoreService {

    @Autowired
    private TStoreMapper storeMapper;

    @Override
    public Result getList(String keyword, Long pageNum, Long pageSize) {
        Page<TStore> tStorePage = new Page<>(pageNum, pageSize);
        QueryWrapper<TStore> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(keyword)) {
            queryWrapper.like("store_name", keyword);
        }
        Page<TStore> page = storeMapper.selectPage(tStorePage, queryWrapper);
        StoreListVo storeListVo = new StoreListVo();
        storeListVo.setList(page.getRecords());
        storeListVo.setTotal(page.getTotal());
        return Result.success(storeListVo);
    }
}
