package com.board_of_ads.repository;

import com.board_of_ads.models.kladr.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    City findById(long id);

    @Query("select c from City c where c.name like :name%")
    List<City> findAllByName(@Param("name") String name);

    @Query("select c from City c where c.region.id = :id")
    List<City> findByRegionId(@Param("id") Long id);

    City findCityByName(String name);


}
