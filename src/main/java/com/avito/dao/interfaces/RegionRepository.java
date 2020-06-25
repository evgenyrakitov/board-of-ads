package com.avito.dao.interfaces;

import com.avito.models.kladr.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("select r from Region r where r.name = :name")
    Region findByName(@Param("name") String name);

}
