package com.maks.voting.service;

import com.maks.voting.model.Theme;
import com.maks.voting.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.maks.voting.util.ValidationUtil.checkNotFound;

@Service
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Theme create(Theme theme) {
        Assert.notNull(theme, "Theme must not be null");
        theme.getItems().forEach(item -> item.setTheme(theme));
        return themeRepository.save(theme);
    }

    @Override
    public Theme updateEnabled(Theme theme, boolean bool) {
        Assert.notNull(theme, "Theme must not be null");
        theme.setEnabled(bool);
        return checkNotFound(themeRepository.save(theme), "Theme");
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
