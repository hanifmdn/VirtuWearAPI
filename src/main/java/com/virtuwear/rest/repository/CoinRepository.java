package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {

}
