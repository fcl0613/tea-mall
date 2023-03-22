package com.wwx.teamall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.entity.vo.LoginVo;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.TUserMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.SellerService;
import com.wwx.teamall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

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
}
