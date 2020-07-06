package com.avito.controllers.simple;

import com.avito.models.posting.Posting;
import com.avito.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {

SearchService searchService;
    @GetMapping
    public String getSearchForm(@RequestParam("category") String category,
                                @RequestParam("search") String search,
                                @RequestParam("region") String region,
                                Model model){
        List<Posting> postingList = searchService.getSearchPostings(search);
        String h1 = category+" в "+region;
        model.addAttribute("postingList", postingList);
        model.addAttribute("h1", h1);
    return "search-page";
    }


}
