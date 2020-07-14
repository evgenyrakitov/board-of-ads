package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.service.interfaces.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    private RegionService regionService;

    public MainPageController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        List<Region> regions = regionService.getAllRegions();
        model.addAttribute("regions", regions);
        return "main-page";
    }
}
