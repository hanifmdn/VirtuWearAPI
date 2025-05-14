package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, String> {
}
