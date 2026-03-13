package auca.ac.rw.literaturePreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.rw.literaturePreservation.domain.Location;
import auca.ac.rw.literaturePreservation.domain.Storyteller;

@Repository
public interface StorytellerRepository extends JpaRepository<Storyteller, Long> {
    
    boolean existsByEmail(String email);

    List<Storyteller> findByLocation(Location location);

    List<Storyteller> findByLocationIn(List<Location> locations);
}
