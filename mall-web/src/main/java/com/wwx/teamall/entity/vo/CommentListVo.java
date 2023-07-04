package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TComment;
import lombok.Data;

import java.util.List;

@Data
public class CommentListVo {
    private List<TComment> list;
    private Long total;
}
