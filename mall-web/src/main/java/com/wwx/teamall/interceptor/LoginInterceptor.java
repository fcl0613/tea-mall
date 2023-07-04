package com.wwx.teamall.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.enums.UserRoleEnum;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.TUserMapper;
import com.wwx.teamall.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private JWTUtil jwtUtil;

    @Autowired
    private TUserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入权限拦截器");
        //http的header中获得token
        String token = request.getHeader("Authorization");
        //token不存在
        if (token == null || token.equals("")) throw new BadRequestException("无权限，请先登录");
        //验证token
        if (jwtUtil.isTokenExpired(token)) throw new BadRequestException("连接过期，请重新登录");
        String username = jwtUtil.getUsername(token);
        if (username == null) {
            throw new BadRequestException("无效token，请从新登录");
        }
        List<TUser> users = userMapper.selectList(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getUsername, username));
        if (users.isEmpty()) throw new BadRequestException("非系统用户");
        return true;
    }
}
