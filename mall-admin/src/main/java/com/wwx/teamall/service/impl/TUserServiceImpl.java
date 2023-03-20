package com.wwx.teamall.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.entity.vo.UserVo;
import com.wwx.teamall.enums.UserRoleEnum;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.mapper.TUserMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-18
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {


    @Autowired
    private TUserMapper userMapper;

    @Override
    public Result create(TUser user) {
        Integer count = userMapper.selectCount(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getUsername, user.getUsername()));
        if (count > 0) {
            throw new BadRequestException("当前用户名已存在，请更换后重试");
        }
        // 默认密码
        String password = DigestUtil.md5Hex("123456");
        user.setPassword(password);
        // 默认头像
        user.setAvatar("default.jpg");
        user.setRole(UserRoleEnum.COMMON_USER.getCode());
        user.setNickName(RandomUtil.randomString(10));
        this.save(user);
        return Result.success("添加成功");
    }

    @Override
    public Result getList(String keyword, Long pageNum, Long pageSize) {
        Page<TUser> tUserPage = new Page<>(pageNum, pageSize);
        Page<TUser> page = userMapper.getUserPage(tUserPage, keyword);
        UserVo userVo = new UserVo();
        userVo.setList(page.getRecords());
        userVo.setTotal(page.getTotal());
        return Result.success(userVo);
    }

    @Override
    public Result updateUser(TUser user) {
        this.updateById(user);
        return Result.success("更新成功");
    }

    @Override
    public Result delete(Integer id) {
        this.removeById(id);
        return Result.success("删除成功");
    }

    @Override
    public Result getInfo(Integer id) {
        TUser user = this.getById(id);
        return Result.success(user);
    }
}
