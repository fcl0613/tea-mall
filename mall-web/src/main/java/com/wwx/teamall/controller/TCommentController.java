package com.wwx.teamall.controller;


import com.wwx.teamall.entity.DTO.CreateCommentDTO;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/comment")
public class TCommentController {

    @Autowired
    private TCommentService commentService;

    @GetMapping("/list")
    public Result getCommentList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Long pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                                 @RequestParam("goodsId") Integer goodsId) {
       return commentService.getCommentList(pageNum, pageSize, goodsId);
    }

    @PostMapping("/create")
    public Result createComment(@RequestBody CreateCommentDTO createCommentDTO) {
        return commentService.createComment(createCommentDTO);
    }
}

