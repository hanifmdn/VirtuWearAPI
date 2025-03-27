package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.SingleGarment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SingleGarmentRepository extends JpaRepository<SingleGarment, Long> {
    List<SingleGarment> findByUserUid(String userId);

}
