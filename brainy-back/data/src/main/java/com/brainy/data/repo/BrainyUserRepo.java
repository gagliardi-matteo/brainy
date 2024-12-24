package com.brainy.data.repo;

import com.brainy.data.domain.BrainyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrainyUserRepo extends JpaRepository<BrainyUser, Long> {

    BrainyUser findByEmail(String email);

    BrainyUser findByRefreshToken(String refreshToken);
}
