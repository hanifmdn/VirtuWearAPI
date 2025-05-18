package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.Model;
import com.virtuwear.rest.entity.Tryon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> findByUserUid(String userUid);

}
