package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.Tryon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    Optional<Coin> findByUserUid(String userUid);

}
