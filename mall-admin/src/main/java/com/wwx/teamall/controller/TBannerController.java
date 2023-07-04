package com.wwx.teamall.controller;


import com.wwx.teamall.entity.TBanner;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/banner")
public class TBannerController {

    @Autowired
    private TBannerService bannerService;

    @PostMapping("/add")
    public Result add(@RequestBody TBanner banner) {
        return bannerService.addBanner(banner);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return bannerService.delete(id);
    }

    @PostMapping("/publish")
    public Result publishBanner(@RequestParam("id") Integer id) {
        return bannerService.publishBanner(id);
    }

    @PostMapping("/unPublish")
    public Result unPublishBanner(@RequestParam("id") Integer id) {
        return bannerService.unPublishBanner(id);
    }

    @RequestMapping("/list")
    public Result getBannerList(@RequestParam("pageNum") Long pageNum,
                                @RequestParam("pageSize") Long pageSize) {
        return bannerService.getBannerList(pageNum, pageSize);
    }
}

