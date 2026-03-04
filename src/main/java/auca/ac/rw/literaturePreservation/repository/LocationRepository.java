package auca.ac.rw.literaturePreservation.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.rw.literaturePreservation.domain.ELocationType;
import auca.ac.rw.literaturePreservation.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
    Optional<Location> findByCode(String code);

    Optional<Location> findByNameAndType(String name, ELocationType type);

    List<Location> findByParent_Location_id(Long parentId);

    boolean existsByCode(String code);
}
