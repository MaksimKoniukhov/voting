package com.maks.voting.controller;

import com.maks.voting.model.Item;
import com.maks.voting.model.Theme;
import com.maks.voting.service.ItemService;
import com.maks.voting.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("voting")
@Slf4j
public class VotingController {

    private final ThemeService themeService;

    private final ItemService itemService;

    @Autowired
    public VotingController(ThemeService themeService, ItemService itemService) {
        this.themeService = themeService;
        this.itemService = itemService;
    }

    @PostMapping("create")
    public Theme createTheme(@RequestBody Theme theme) {
        log.info("create {}", theme);
        return themeService.create(theme);
    }


    @PutMapping("start/{themeId}")
    public String startVoting(@PathVariable("themeId") Theme theme) {
        log.info("start method, get {}", theme);
        return themeService.startVoting(theme);
    }

    @PutMapping("stop/{themeId}")
    public Theme stopVoting(@PathVariable("themeId") Theme theme) {
        log.info("stop method, get {}", theme);
        return themeService.stopVoting(theme);
    }

    @GetMapping("theme/{themeId}/voices/{itemId}")
    public Item getItem(@PathVariable("themeId") Theme theme, @PathVariable("itemId") Item item) {
        log.info("get {} of {}", item, theme);
        return itemService.getItemOfTheme(theme, item);
    }

    @GetMapping("theme/{themeId}")
    public Theme getTheme(@PathVariable("themeId") Theme theme) {
        log.info("get {}", theme);
        return themeService.get(theme);
    }

    @PutMapping("theme/{themeId}/item/{itemId}")
    public Item voteRegistration(@PathVariable("themeId") Theme theme, @PathVariable("itemId") Item item) {
        log.info("vote registration for {} of {} ", item, theme);
        return itemService.voiceRegistration(theme, item);
    }

    @GetMapping                                                            //optional
    public List<Theme> getAllThemes() {
        log.info("getAllThemes");
        return themeService.getAll();
    }

    @DeleteMapping("theme/{themeId}")                                     //optional
    public Theme deleteTheme(@PathVariable("themeId") Theme theme) {
        log.info("delete {}", theme);
        themeService.delete(theme);
        return theme;
    }
}
