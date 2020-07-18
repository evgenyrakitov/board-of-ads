package com.board_of_ads.repository;

import com.board_of_ads.models.kladr.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findById(long id);

    @Query("select r from Region r where r.name like :name%")
    List<Region> findByName(@Param("name") String name);

    @Query("select r from Region r where r.beatyName like :beautyName%")
    List<Region> findByBeautyName(@Param("beautyName") String beautyName);

    Region findRegionByName(String name);

}
