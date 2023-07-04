package com.wwx.teamall.entity.vo;

import com.wwx.teamall.entity.TBanner;
import lombok.Data;

import java.util.List;

@Data
public class HomeContentVo {
    private List<TBanner> bannerList;
    private List<HomeCategoryVo> categoryList;
}
