package com.board_of_ads.repository.custom;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PostingRepositoryCustomImpl implements PostingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Posting> customSearchPostings(Category category, String searchString, Region region, City city, boolean onlyTitle, boolean onlyWithImages) {
        StringBuilder query = new StringBuilder("select distinct p from Posting p where 1=1 ");

        if (category != null) {
            query.append(" and p.category = ").append(category.getId());
        }
        if (searchString != null && (searchString.length() >= 3)) {
            if (onlyTitle) {
                query.append(" and lower(p.title) like lower('").append(searchString).append("') ");
            } else {
                query.append(" and ( lower(p.title) like lower('").append(searchString).append("') or lower(p.fullDescription) like lower('").append(searchString).append("')) ");
            }
        }
        if (city != null) {
            query.append(" and p.cityId = ").append(city.getId()).append(" ");
        } else if (region != null) {
            query.append(" and p.regionId = ").append(region.getId()).append(" ");
        }


        //System.out.println(query.toString());
        return entityManager.createQuery(query.toString()).getResultList();
    }

    @Override
    public <S extends Posting> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Posting> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Posting> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Posting> findAll() {
        return null;
    }

    @Override
    public Iterable<Posting> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Posting entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Posting> entities) {

    }

    @Override
    public void deleteAll() {

    }


//    @Query("select p from Posting p where p.category = :category and p.cityId = :name" +
//            " and p.title like : search% and p.imagePath.size = :images")
//    List<Posting> getSearchPostings(@Param("category") Category category,
//                                    @Param("name") String name,
//                                    @Param("search") String search,
//                                    @Param("images") int images);


}

