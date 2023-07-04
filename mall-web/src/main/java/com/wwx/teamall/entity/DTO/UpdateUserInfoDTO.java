package com.wwx.teamall.entity.DTO;

import lombok.Data;

@Data
public class UpdateUserInfoDTO {
    private String nickName;
    private String phone;
    private String birthday;
    private Integer sex;
}
