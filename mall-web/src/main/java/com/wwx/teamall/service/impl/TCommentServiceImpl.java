package com.wwx.teamall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.DTO.CreateCommentDTO;
import com.wwx.teamall.entity.TComment;
import com.wwx.teamall.entity.TGoods;
import com.wwx.teamall.entity.TOrderDetail;
import com.wwx.teamall.entity.TUser;
import com.wwx.teamall.entity.vo.CommentListVo;
import com.wwx.teamall.enums.GoodsHasCommentEnum;
import com.wwx.teamall.mapper.TCommentMapper;
import com.wwx.teamall.mapper.TGoodsMapper;
import com.wwx.teamall.mapper.TOrderDetailMapper;
import com.wwx.teamall.mapper.TUserMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TCommentService;
import com.wwx.teamall.utils.HttpUtil;
import com.wwx.teamall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-04-02
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private TOrderDetailMapper orderDetailMapper;

    @Autowired
    private TGoodsMapper goodsMapper;

    @Autowired
    private TUserMapper userMapper;

    @Override
    public Result getCommentList(Long pageNum, Long pageSize, Integer goodsId) {
        Page<TComment> commentPage = new Page<>(pageNum, pageSize);
        Page<TComment> page = this.page(commentPage, new LambdaQueryWrapper<TComment>()
                .eq(TComment::getGoodsId, goodsId)
        .orderByDesc(TComment::getCreateTime));
        CommentListVo commentListVo = new CommentListVo();
        commentListVo.setList(page.getRecords());
        commentListVo.setTotal(page.getTotal());
        return Result.success(commentListVo);
    }

    @Override
    public Result createComment(CreateCommentDTO createCommentDTO) {
        TComment comment = new TComment();
        comment.setContent(createCommentDTO.getContent());
        TOrderDetail orderDetail = orderDetailMapper.selectById(createCommentDTO.getId());
        TGoods goods = goodsMapper.selectById(orderDetail.getGoodsId());
        TUser user = userMapper.selectById(getUserId());
        comment.setCreateTime(LocalDateTime.now());
        comment.setGoodsId(orderDetail.getGoodsId());
        comment.setGoodsName(orderDetail.getGoodsName());
        comment.setOrderId(orderDetail.getOrderId());
        comment.setStoreId(goods.getStoreId());
        comment.setUserAvatar(user.getAvatar());
        comment.setUserId(user.getId());
        comment.setUserName(user.getNickName());
        this.save(comment);
        orderDetailMapper.update(null, new LambdaUpdateWrapper<TOrderDetail>()
        .eq(TOrderDetail::getId, createCommentDTO.getId())
        .set(TOrderDetail::getHasCommented, GoodsHasCommentEnum.COMMENTED.getCode()));
        return Result.success();
    }

    private Integer getUserId() {
        HttpServletRequest request = HttpUtil.getRequest();
        String authorization = request.getHeader("Authorization");
        return jwtUtil.getUserId(authorization);
    }
}
