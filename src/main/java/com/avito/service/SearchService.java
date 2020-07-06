package com.avito.service;

import com.avito.models.Category;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.CategoryService;
import com.avito.service.interfaces.PostingService;
import com.avito.service.interfaces.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
    private CategoryService categoryService;
    private RegionService regionService;
    private PostingService postingService;

    public List<Posting> getSearchPostings(String search){

        List<Posting> postingList = postingService.getAllPostings();
        List<Posting> newPostingList = new ArrayList<>();

        for (int i = 0; i < postingList.size(); i++) {
            Posting posting = postingList.get(i);
            String[] fullDescriptions = posting.getFullDescription().split(" ");
            for (String fullDescription:fullDescriptions) {
                if (search.contains(fullDescription.toLowerCase())){
                    newPostingList.add(posting);
                    break;
                }
            }
        }
        return newPostingList;
    }
}
