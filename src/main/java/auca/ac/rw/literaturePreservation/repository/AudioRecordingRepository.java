package auca.ac.rw.literaturePreservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.rw.literaturePreservation.domain.AudioRecording;

public interface AudioRecordingRepository extends JpaRepository<AudioRecording, Long> {
    
    Optional<AudioRecording> findByLiteratureId(Long literatureId);

    boolean existsByLiteratureId(Long literatureId);
}
