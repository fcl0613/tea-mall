package com.wwx.teamall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwx.teamall.entity.DTO.CreateCommentDTO;
import com.wwx.teamall.entity.TComment;
import com.wwx.teamall.model.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwx
 * @since 2023-04-02
 */
public interface TCommentService extends IService<TComment> {

    Result getCommentList(Long pageNum, Long pageSize, Integer goodsId);

    Result createComment(CreateCommentDTO createCommentDTO);
}
