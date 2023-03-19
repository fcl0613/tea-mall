package com.wwx.teamall.controller;


import com.wwx.teamall.entity.TCategory;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/category")
public class TCategoryController {

    @Autowired
    private TCategoryService tCategoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result create(TCategory category) {
        return tCategoryService.create(category);
    }

    @RequestMapping("/list")
    public Result getList(@RequestParam("keyword") String keyword,
                          @RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        return tCategoryService.getList(keyword, pageNum, pageSize);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(TCategory category) {
        return tCategoryService.updateCategory(category);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestParam("id") Integer id) {
        return tCategoryService.delete(id);
    }

    @RequestMapping("/info")
    public Result getInfo(@RequestParam("id") Integer id) {
        return tCategoryService.getInfo(id);
    }

    @RequestMapping("/parent")
    public Result getAllParentCategory() {
        return tCategoryService.getAllParentCategory();
    }
}

