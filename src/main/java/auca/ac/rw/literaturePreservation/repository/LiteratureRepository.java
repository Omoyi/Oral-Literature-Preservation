package auca.ac.rw.literaturePreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.rw.literaturePreservation.domain.Literature;

@Repository
public interface LiteratureRepository extends JpaRepository<Literature, Long> {

    // Pagination + sorting supported automatically via Pageable
    Page<Literature> findAll(Pageable pageable);

    // Example: filter by storyteller (also pageable)
    Page<Literature> findByStorytellerId(Long storytellerId, Pageable pageable);

    boolean existsByTitle(String title);
}
