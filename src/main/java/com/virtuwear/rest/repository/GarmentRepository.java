package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.Garment;
import com.virtuwear.rest.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarmentRepository extends JpaRepository<Garment, String> {
    List<Garment> findByUserUid(String userUid);
}
