package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TCategory;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    private List<TCategory> list;
    private Long total;
}
