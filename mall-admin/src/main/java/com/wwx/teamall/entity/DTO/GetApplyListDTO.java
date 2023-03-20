package com.wwx.teamall.entity.DTO;

import lombok.Data;

@Data
public class GetApplyListDTO {
    private String name;
    private String phone;
    private String storeName;
    private Long pageNum;
    private Long pageSize;
    private Integer status;
}
