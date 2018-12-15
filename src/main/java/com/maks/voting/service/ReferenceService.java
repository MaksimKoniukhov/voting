package com.maks.voting.service;

import com.maks.voting.model.Reference;

public interface ReferenceService {
    Reference create(Reference reference);

    Reference getByThemeId(long themeId);

    void delete(Reference reference);
}
