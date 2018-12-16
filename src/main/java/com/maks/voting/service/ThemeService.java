package com.maks.voting.service;

import com.maks.voting.model.Reference;
import com.maks.voting.model.Theme;

import java.util.List;

public interface ThemeService {
    Theme create(Theme theme);

    Reference startVoting(Theme theme);

    Theme stopVoting(Theme theme);

    void delete(Theme theme);

    Theme get(Theme theme);

    List<Theme> getAll();
}
