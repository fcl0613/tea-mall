package com.wwx.teamall.entity.DO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsCommentsDO {
    private String avatar;
    private String nickName;
    private String content;
    private LocalDateTime createTime;
}
