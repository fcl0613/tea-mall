package com.wwx.teamall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

import java.util.List;

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
        return Result.success();
    }

    @Override
    public Result getList(String keyword, Long pageNum, Long pageSize) {
        Page<TCategory> tCategoryPage = new Page<>(pageNum, pageSize);
        QueryWrapper<TCategory> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(keyword)) {
            queryWrapper.like("category_name", keyword);
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
        return Result.success();
    }

    @Override
    public Result delete(Integer id) {
        this.removeById(id);
        return Result.success();
    }

    @Override
    public Result getInfo(Integer id) {
        TCategory category = this.getById(id);
        return Result.success(category);
    }

    @Override
    public Result getAllParentCategory() {
        List<TCategory> tCategories = categoryMapper.selectList(new LambdaQueryWrapper<TCategory>()
                .eq(TCategory::getParentId, 0));
        TCategory category = new TCategory();
        category.setId(0);
        category.setCategoryName("一级分类");
        tCategories.add(0, category);
        return Result.success(tCategories);
    }
}
