package com.maks.voting.service;

import com.maks.voting.model.Theme;

import java.util.List;

public interface ThemeService {
    Theme create(Theme theme);

    String startVoting(Theme theme);

    Theme stopVoting(Theme theme);

    void delete(Theme theme);

    Theme get(Theme theme);

    List<Theme> getAll();
}
