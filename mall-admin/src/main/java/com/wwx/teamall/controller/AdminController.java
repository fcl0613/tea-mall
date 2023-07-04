package com.wwx.teamall.controller;


import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-02-19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TAdminService tAdminService;

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        return tAdminService.login(username, password);
    }


}

