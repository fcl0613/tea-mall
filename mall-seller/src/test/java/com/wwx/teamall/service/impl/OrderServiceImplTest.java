package com.wwx.teamall.service.impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class OrderServiceImplTest {
    @Test
    public void test() {
        String time = "2023-04-01";
        DateTime parse1 = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
        System.out.println(parse1);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime parse = LocalDateTime.parse(time, dateTimeFormatter);
//        System.out.println(parse);
    }
}