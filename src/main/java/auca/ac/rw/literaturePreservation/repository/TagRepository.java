package auca.ac.rw.literaturePreservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.rw.literaturePreservation.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsByName(String name);

    Optional<Tag> findByName(String name);
    
}
