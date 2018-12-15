package com.maks.voting.service;

import com.maks.voting.model.Reference;
import com.maks.voting.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.maks.voting.util.ValidationUtil.checkNotFound;

@Service
public class ReferenceServiceImpl implements ReferenceService {
    private final ReferenceRepository referenceRepository;

    @Autowired
    public ReferenceServiceImpl(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    @Override
    public Reference create(Reference reference) {
        Assert.notNull(reference, "Reference must not be null");
        return referenceRepository.save(reference);
    }

    @Override
    public Reference getByThemeId(long themeId) {
        return checkNotFound(referenceRepository.findByThemeId(themeId), "Reference");
    }

    @Override
    public void delete(Reference reference) {
        referenceRepository.delete(checkNotFound(reference, "Reference"));
    }
}
