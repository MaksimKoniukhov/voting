package com.maks.voting.repository;

import com.maks.voting.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    Reference findByThemeId(long id);
}
