package com.wwx.teamall.controller;

import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public Result register(@RequestParam("phone") String phone,
                           @RequestParam("password") String password) {
        return userService.register(phone, password);
    }

    @GetMapping("/test")
    public String test() {
        return "1111";
    }
}
