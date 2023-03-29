package com.wwx.teamall.service;

import com.wwx.teamall.entity.DTO.UpdatePasswordDTO;
import com.wwx.teamall.entity.DTO.UpdateUserInfoDTO;
import com.wwx.teamall.model.Result;

public interface UserService {
    Result login(String username, String password);

    Result register(String phone, String password);

    Result getUserInfo();

    Result updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO);

    Result updateAvatar(String pl);

    Result updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
