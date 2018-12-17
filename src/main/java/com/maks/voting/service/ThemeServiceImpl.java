package com.maks.voting.service;

import com.maks.voting.model.Theme;
import com.maks.voting.repository.ThemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.maks.voting.util.ValidationUtil.checkNew;
import static com.maks.voting.util.ValidationUtil.checkNotFound;

@Service
@Slf4j
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Theme create(Theme theme) {
        Assert.notNull(theme, "Theme must not be null");
        checkNew(theme);
        Assert.notNull(theme.getItems(), "Items must not be null");
        theme.getItems().forEach(item -> {
            Assert.notNull(item, "Item must not be null");
            checkNew(item);
            item.setTheme(theme);
        });
        return themeRepository.save(theme);
    }

    @Override
    public String startVoting(Theme theme) {
        checkNotFound(theme, "Theme");
        if (theme.getPath() != null)
            return theme.getPath();

        log.info("start {}", theme);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String path = String.format("%s/voting/theme/%d", url, theme.getId());

        theme.setPath(path);
        themeRepository.save(theme);

        return path;
    }

    @Override
    public Theme stopVoting(Theme theme) {
        checkNotFound(theme, "Theme");
        if (theme.getPath() == null)
            return theme;

        log.info("stop{}", theme);

        theme.setPath(null);
        return themeRepository.save(theme);
    }

    @Override
    public void delete(Theme theme) {
        themeRepository.delete(checkNotFound(theme, "Theme"));
    }

    @Override
    public Theme get(Theme theme) {
        return checkNotFound(theme, "Theme");
    }

    @Override
    public List<Theme> getAll() {
        return themeRepository.findAll();
    }
}
