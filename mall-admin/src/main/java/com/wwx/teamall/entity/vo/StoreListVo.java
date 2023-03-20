package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TStore;
import lombok.Data;

import java.util.List;

@Data
public class StoreListVo {
    private List<TStore> list;
    private Long total;
}
