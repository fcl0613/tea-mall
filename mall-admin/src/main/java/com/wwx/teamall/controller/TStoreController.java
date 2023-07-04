package com.wwx.teamall.controller;


import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/store")
public class TStoreController {

    @Autowired
    private TStoreService storeService;

    @RequestMapping(value = "list")
    public Result getStoreList(@RequestParam("keyword") String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        return storeService.getList(keyword, pageNum, pageSize);
    }
}

