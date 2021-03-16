package io.github.mat3e.lang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LangRepository extends JpaRepository<Lang, Integer> {
    Optional<Lang> findByCode(String code);

}
