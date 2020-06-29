package com.avito.repository;

import com.avito.models.kladr.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("select r from Region r where r.name like :name%")
    List<Region> findByName(@Param("name") String name);

}
