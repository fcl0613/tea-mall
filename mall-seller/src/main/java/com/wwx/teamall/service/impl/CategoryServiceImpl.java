package com.wwx.teamall.service.impl;

import com.wwx.teamall.entity.TCategory;
import com.wwx.teamall.entity.vo.CategoryOptionsVo;
import com.wwx.teamall.mapper.TCategoryMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private TCategoryMapper categoryMapper;

    @Override
    public Result getAll() {
        List<TCategory> categories = categoryMapper.selectList(null);
        ArrayList<CategoryOptionsVo> categoryOptionsVos = new ArrayList<>();
        for (TCategory category : categories) {
            if (0 == category.getParentId()) {
                CategoryOptionsVo categoryOptionsVo = new CategoryOptionsVo();
                categoryOptionsVo.setValue(category.getId());
                categoryOptionsVo.setLabel(category.getCategoryName());
                ArrayList<CategoryOptionsVo> categoryOptionsVos1 = new ArrayList<>();
                for (TCategory tCategory : categories) {
                    if (tCategory.getParentId() == category.getId()) {
                        CategoryOptionsVo categoryOptionsVo1 = new CategoryOptionsVo();
                        categoryOptionsVo1.setLabel(tCategory.getCategoryName());
                        categoryOptionsVo1.setValue(tCategory.getId());
                        categoryOptionsVos1.add(categoryOptionsVo1);
                    }
                }
                categoryOptionsVo.setChildren(categoryOptionsVos1);
                categoryOptionsVos.add(categoryOptionsVo);
            }
        }
        return Result.success(categoryOptionsVos);
    }
}
