package com.wwx.teamall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.TCategory;
import com.wwx.teamall.entity.vo.CategoryVo;
import com.wwx.teamall.mapper.TCategoryMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-18
 */
@Service
public class TCategoryServiceImpl extends ServiceImpl<TCategoryMapper, TCategory> implements TCategoryService {

    @Autowired
    private TCategoryMapper categoryMapper;

    @Override
    public Result create(TCategory category) {
        this.save(category);
        return Result.success("添加成功");
    }

    @Override
    public Result getList(String keyword, Long pageNum, Long pageSize) {
        Page<TCategory> tCategoryPage = new Page<>(pageNum, pageSize);
        QueryWrapper<TCategory> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(keyword)) {
            queryWrapper.eq("category_name", keyword);
        }
        Page<TCategory> page = categoryMapper.selectPage(tCategoryPage, queryWrapper);
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setList(page.getRecords());
        categoryVo.setTotal(page.getTotal());
        return Result.success(categoryVo);
    }

    @Override
    public Result updateCategory(TCategory category) {
        this.updateById(category);
        return Result.success("更新成功");
    }

    @Override
    public Result delete(Integer id) {
        this.removeById(id);
        return Result.success("删除成功");
    }

    @Override
    public Result getInfo(Integer id) {
        TCategory category = this.getById(id);
        return Result.success(category);
    }
}
