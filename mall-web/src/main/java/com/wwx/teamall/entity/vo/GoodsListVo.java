package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.DO.GoodsListDO;
import lombok.Data;

import java.util.List;

@Data
public class GoodsListVo {
    private List<GoodsListDO> list;
    private Long total;
}
