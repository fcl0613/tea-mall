package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TUser;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private List<TUser> list;
    private Long total;
}
