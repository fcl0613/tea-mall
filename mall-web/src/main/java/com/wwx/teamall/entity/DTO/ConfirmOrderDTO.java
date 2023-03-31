package com.wwx.teamall.entity.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ConfirmOrderDTO {
    private List<Integer> cartIdList;
}
