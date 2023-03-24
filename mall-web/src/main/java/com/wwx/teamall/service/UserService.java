package com.wwx.teamall.service;

import com.wwx.teamall.model.Result;

public interface UserService {
    Result login(String username, String password);

    Result register(String phone, String password);
}
