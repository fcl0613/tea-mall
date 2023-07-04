package com.wwx.teamall.entity.DTO;

import lombok.Data;

@Data
public class GetOrderListDTO {
    private Long pageNum;
    private Long pageSize;
    private Integer status;
}
