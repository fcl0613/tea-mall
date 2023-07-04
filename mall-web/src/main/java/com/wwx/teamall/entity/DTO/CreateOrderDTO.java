package com.wwx.teamall.entity.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    private Integer addressId;
    private List<Integer> cartIds;
}
