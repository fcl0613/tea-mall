package com.wwx.teamall.entity.DTO;

import lombok.Data;

@Data
public class GetOrderListDTO {
    private Long pageNum;
    private Long pageSize;
    private String orderId;
    private Integer orderStatus;
    private String beginTime;
    private String endTime;
}
