package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.Garment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarmentRepository extends JpaRepository<Garment, String> {

}
