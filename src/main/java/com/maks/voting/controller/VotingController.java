package com.maks.voting.controller;

import com.maks.voting.model.Item;
import com.maks.voting.model.Reference;
import com.maks.voting.model.Theme;
import com.maks.voting.service.ItemService;
import com.maks.voting.service.ReferenceService;
import com.maks.voting.service.ThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("voting")
public class VotingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ThemeService themeService;

    private final ItemService itemService;

    private final ReferenceService referenceService;

    @Autowired
    public VotingController(ThemeService themeService, ItemService itemService, ReferenceService referenceService) {
        this.themeService = themeService;
        this.itemService = itemService;
        this.referenceService = referenceService;
    }

    @PostMapping("create")
    public Theme createTheme(@RequestBody Theme theme) {
        logger.info("create {}", theme);
        return themeService.create(theme);
    }


    @PutMapping("start/{themeId}")
    public Reference startVoting(@PathVariable("themeId") Theme theme) {
        logger.info("start method, get {}", theme);
        if (theme.isEnabled())
            return referenceService.getByThemeId(theme.getId());

        logger.info("start {}", theme);

        Theme updateTheme = themeService.updateEnabled(theme, true);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String path = String.format("%s/voting/theme/%d", url, updateTheme.getId());
        Reference reference = new Reference(path, updateTheme);

        return referenceService.create(reference);
    }

    @PutMapping("stop/{themeId}")
    public Theme stopVoting(@PathVariable("themeId") Theme theme) {
        logger.info("stop method, get {}", theme);
        if (!theme.isEnabled())
            return theme;

        logger.info("stop{}", theme);

        Theme updateTheme = themeService.updateEnabled(theme, false);

        Reference reference = referenceService.getByThemeId(theme.getId());
        referenceService.delete(reference);

        return updateTheme;
    }

    @GetMapping("theme/{themeId}/voices/{itemId}")
    public Item getItem(@PathVariable("themeId") Theme theme, @PathVariable("itemId") Item item) {
        logger.info("get {} of {}", item, theme);
        return itemService.getItemOfTheme(theme, item);
    }

    @GetMapping("theme/{themeId}")
    public Theme getTheme(@PathVariable("themeId") Theme theme) {
        logger.info("get {}", theme);
        return themeService.get(theme);
    }

    @PutMapping("theme/{themeId}/item/{itemId}")
    public Item voteRegistration(@PathVariable("themeId") Theme theme, @PathVariable("itemId") Item item) {
        logger.info("vote registration for {} of {} ", item, theme);
        return itemService.voiceRegistration(theme, item);
    }

    @GetMapping                                                            //optional
    public List<Theme> getAllThemes() {
        logger.info("getAllThemes");
        return themeService.getAll();
    }

    @DeleteMapping("theme/{themeId}/delete")                               //optional
    public Theme deleteTheme(@PathVariable("themeId") Theme theme) {
        logger.info("delete {}", theme);
        themeService.delete(theme);
        return theme;
    }
}
