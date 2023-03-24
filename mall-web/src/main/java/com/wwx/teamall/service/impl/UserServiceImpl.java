package com.wwx.teamall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.entity.vo.LoginVo;
import com.wwx.teamall.enums.UserRoleEnum;
import com.wwx.teamall.enums.UserSexEnum;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.TUserMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.UserService;
import com.wwx.teamall.utils.JWTUtil;
import com.wwx.teamall.utils.RandomGenerateUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final String DEFAULT_AVATAR = "default.jpg";

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Result login(String username, String password) {
        List<TUser> users = userMapper.selectList(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getUsername, username));
        if (users.isEmpty()) {
            throw new BadRequestException("用户名不存在");
        }
        if (users.size() > 1) {
            throw new BadRequestException("用户名不唯一， 请联系管理");
        }
        TUser user = users.get(0);
        if (!user.getPassword().equals(DigestUtil.md5Hex(password))) {
            throw new BadRequestException("密码错误");
        }
        String token = jwtUtil.createToken(user.getId(), user.getUsername());
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return Result.success(loginVo);
    }

    @Override
    public Result register(String phone, String password) {
        Integer count = userMapper.selectCount(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getPhone, phone));
        if (count > 0) {
            throw new BadRequestException("该手机号已注册");
        }
        String username = "";
        while (true) {
            username = RandomGenerateUserName.generateUsername();
            Integer count1 = userMapper.selectCount(new LambdaQueryWrapper<TUser>()
                    .eq(TUser::getUsername, username));
            if (count1 == 0) {
                break;
            }
        }
        TUser user = new TUser();
        user.setNickName(username);
        user.setRole(UserRoleEnum.COMMON_USER.getCode());
        user.setAvatar(DEFAULT_AVATAR);
        user.setPassword(DigestUtil.md5Hex(password));
        user.setPhone(phone);
        user.setUsername(username);
        user.setSex(UserSexEnum.UN_KNOW.getCode());
        userMapper.insert(user);
        return Result.success();
    }
}
