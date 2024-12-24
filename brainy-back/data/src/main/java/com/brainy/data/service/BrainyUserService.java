package com.brainy.data.service;

import com.brainy.data.domain.BrainyUser;

public interface BrainyUserService {

    public BrainyUser findUserByEmail(String email);
    public BrainyUser registerUser(String email, String name);
    public BrainyUser findByRereshToken(String refreshToken);
    public void disableUser(String email);
    public String updateRefreshToken(String token, String email);

}
