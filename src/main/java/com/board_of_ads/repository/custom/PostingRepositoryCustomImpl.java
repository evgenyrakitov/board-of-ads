package com.board_of_ads.repository.custom;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostingRepositoryCustomImpl {

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

        if (onlyWithImages) {
            query.append(" and p.imagePath.size > 0 ");
        }

        //System.out.println(query.toString());
        return entityManager.createQuery(query.toString()).getResultList();
    }
}

