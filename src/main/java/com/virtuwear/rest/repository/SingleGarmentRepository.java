package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.SingleGarment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface SingleGarmentRepository extends JpaRepository<SingleGarment, Long> {
    List<SingleGarment> findByUserUid(String userId);

    List<SingleGarment> searchByOutfitName(String outfitName);

    List<SingleGarment> findByUserUidAndIsBookmarkTrue(String userId);

    Integer countByUserUid(String userId);

    List<SingleGarment> findByCreatedDateBetween(Timestamp start, Timestamp end);

}
