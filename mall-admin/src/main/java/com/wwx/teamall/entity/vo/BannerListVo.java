package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TBanner;
import lombok.Data;

import java.util.List;

@Data
public class BannerListVo {
    private List<TBanner> list;
    private Long total;
}
