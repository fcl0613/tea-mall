package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TStoreApply;
import lombok.Data;

import java.util.List;

@Data
public class StoreApplyListVo {
    private List<TStoreApply> list;
    private Long total;
}
