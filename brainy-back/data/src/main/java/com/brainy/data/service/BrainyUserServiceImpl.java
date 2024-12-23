package com.brainy.data.service;

import com.brainy.data.domain.BrainyUser;
import com.brainy.data.repo.BrainyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrainyUserServiceImpl implements BrainyUserService{

    @Autowired
    private BrainyUserRepo brainyUserRepo;

    @Override
    public BrainyUser findUserByEmail(String email) {
        return brainyUserRepo.findByEmail(email);
    }

    @Override
    public BrainyUser registerUser(String email, String name) {
        BrainyUser user = new BrainyUser(email, name, true);
        return brainyUserRepo.save(user);
    }

    @Override
    public BrainyUser findByRereshToken(String refreshToken) {
        return brainyUserRepo.findByRefreshToken(refreshToken);
    }

    @Override
    public void disableUser(String email) {
        BrainyUser user = brainyUserRepo.findByEmail(email);
        if(user != null){
            user.setEnabled(false);
            brainyUserRepo.save(user);
        }
    }

    @Override
    public String updateRefreshToken(String token, String email) {
        BrainyUser user = brainyUserRepo.findByEmail(email);
        user.setRefreshToken(token);
        brainyUserRepo.save(user);
        return token;
    }
}
