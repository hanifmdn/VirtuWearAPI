package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.entity.Tryon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TryonRepository extends JpaRepository<Tryon, Long> {
    List<Tryon> findByUserUid(String userUid);

    List<Tryon> searchByOutfitName(String outfitName);

    List<Tryon> findByUserUidAndIsBookmarkTrue(String userUid);

    List<Tryon> findByCreatedDateBetween(Timestamp start, Timestamp end);

    int countByUserUid(String userUid);

}
