package com.wwx.teamall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wwx.teamall.entity.DTO.SellerApplyDTO;
import com.wwx.teamall.entity.DTO.UpdatePasswordDTO;
import com.wwx.teamall.entity.DTO.UpdateUserInfoDTO;
import com.wwx.teamall.entity.TStoreApply;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.entity.vo.LoginVo;
import com.wwx.teamall.entity.vo.UserInfoVo;
import com.wwx.teamall.enums.StoreApplyEnum;
import com.wwx.teamall.enums.UserRoleEnum;
import com.wwx.teamall.enums.UserSexEnum;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.TStoreApplyMapper;
import com.wwx.teamall.mapper.TUserMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.UserService;
import com.wwx.teamall.utils.HttpUtil;
import com.wwx.teamall.utils.JWTUtil;
import com.wwx.teamall.utils.RandomGenerateUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final String DEFAULT_AVATAR = "default.jpg";

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private TStoreApplyMapper storeApplyMapper;

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

    @Override
    public Result getUserInfo() {
        Integer userId = getUserId();
        TUser user = userMapper.selectById(userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setAvatar(user.getAvatar());
        userInfoVo.setBirthday(user.getBirthday());
        userInfoVo.setNickName(user.getNickName());
        userInfoVo.setPhone(user.getPhone());
        userInfoVo.setSex(user.getSex());
        userInfoVo.setRole(user.getRole());
        return Result.success(userInfoVo);
    }

    @Override
    public Result updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO) {
        Integer userId = getUserId();
        TUser user = new TUser();
        user.setSex(updateUserInfoDTO.getSex());
        user.setPhone(updateUserInfoDTO.getPhone());
        user.setNickName(updateUserInfoDTO.getNickName());
        user.setBirthday(updateUserInfoDTO.getBirthday());
        user.setId(userId);
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    public Result updateAvatar(String pl) {
        Integer userId = getUserId();
        userMapper.update(null, new LambdaUpdateWrapper<TUser>()
        .eq(TUser::getId, userId)
        .set(TUser::getAvatar, pl));
        return Result.success();
    }

    @Override
    public Result updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        Integer userId = getUserId();
        TUser user = userMapper.selectById(userId);
        if (!user.getPassword().equals(DigestUtil.md5Hex(updatePasswordDTO.getOldPassword()))) {
            throw new BadRequestException("原密码错误");
        }
        userMapper.update(null, new LambdaUpdateWrapper<TUser>()
        .eq(TUser::getId, userId)
        .set(TUser::getPassword, DigestUtil.md5Hex(updatePasswordDTO.getNewPassword())));
        return Result.success();
    }

    @Override
    public Result sellerApply(SellerApplyDTO sellerApplyDTO) {
        Integer userId = getUserId();
        TStoreApply storeApply = new TStoreApply();
        storeApply.setCreateTime(LocalDateTime.now());
        storeApply.setName(sellerApplyDTO.getName());
        storeApply.setPhone(sellerApplyDTO.getPhone());
        storeApply.setStatus(StoreApplyEnum.PENDING.getCode());
        storeApply.setStoreName(sellerApplyDTO.getStoreName());
        storeApply.setUserId(userId);
        storeApplyMapper.insert(storeApply);
        return Result.success();
    }

    private Integer getUserId () {
        HttpServletRequest request = HttpUtil.getRequest();
        String authorization = request.getHeader("Authorization");
        return jwtUtil.getUserId(authorization);
    }
}
