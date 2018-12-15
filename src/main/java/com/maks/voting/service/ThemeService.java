package com.maks.voting.service;

import com.maks.voting.model.Theme;

import java.util.List;

public interface ThemeService {
    Theme create(Theme theme);

    Theme updateEnabled(Theme theme, boolean bool);

    void delete(Theme theme);

    Theme get(Theme theme);

    List<Theme> getAll();
}
