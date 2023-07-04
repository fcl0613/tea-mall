package com.wwx.teamall.entity.DTO;

import lombok.Data;

@Data
public class CreateCommentDTO {
    // orderdetail表的主键
    private Integer id;
    private String content;
}
