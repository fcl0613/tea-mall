package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TGoods;
import lombok.Data;

import java.util.List;

@Data
public class GoodsListVo {
    private List<TGoods> list;
    private Long total;
}
