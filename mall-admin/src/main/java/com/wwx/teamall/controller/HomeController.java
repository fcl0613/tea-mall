package com.wwx.teamall.controller;

import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/content")
    public Result getHomeContent() {
        return homeService.getHomeContent();
    }
}
