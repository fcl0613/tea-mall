package com.wwx.teamall.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryOptionsVo {
    private String label;
    private Integer value;
    private List<CategoryOptionsVo> children;
}
