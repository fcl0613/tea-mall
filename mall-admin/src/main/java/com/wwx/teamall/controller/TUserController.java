package com.wwx.teamall.controller;


import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/user")
public class TUserController {
    @Autowired
    private TUserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result create(@RequestBody TUser user) {
        return userService.create(user);
    }

    @RequestMapping("/list")
    public Result list(@RequestParam("keyword") String keyword,
                       @RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        return userService.getList(keyword, pageNum, pageSize);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody TUser user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Integer id) {
        return userService.delete(id);
    }

    @RequestMapping("/info")
    public Result getInfo(@RequestParam("id") Integer id) {
        return userService.getInfo(id);
    }
}

