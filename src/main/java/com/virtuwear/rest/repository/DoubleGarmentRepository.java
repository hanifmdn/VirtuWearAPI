package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.DoubleGarment;
import com.virtuwear.rest.entity.SingleGarment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoubleGarmentRepository extends JpaRepository<DoubleGarment, Long> {
    List<DoubleGarment> findByUserUid(String userId);
}
