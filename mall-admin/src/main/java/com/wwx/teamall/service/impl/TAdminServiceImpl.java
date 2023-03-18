package com.wwx.teamall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.TAdmin;
import com.wwx.teamall.entity.vo.LoginVo;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.TAdminMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TAdminService;
import com.wwx.teamall.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-02-19
 */
@Service
@Slf4j
public class TAdminServiceImpl extends ServiceImpl<TAdminMapper, TAdmin> implements TAdminService {

    @Autowired
    private TAdminMapper tAdminMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Result login(String username, String password) {
        List<TAdmin> adminList = tAdminMapper.selectList(new LambdaQueryWrapper<TAdmin>()
                .eq(TAdmin::getUsername, username));
        if (adminList.isEmpty()) {
            throw new BadRequestException("用户名不存在");
        }
        if (adminList.size() > 1) {
            throw new BadRequestException("用户名不唯一， 请联系管理");
        }
        TAdmin admin = adminList.get(0);
        if (!admin.getPassword().equals(DigestUtil.md5Hex(password))) {
            throw new BadRequestException("密码错误");
        }
        String token = jwtUtil.createToken(admin.getId(), admin.getUsername());
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return Result.success(loginVo);
    }
}
