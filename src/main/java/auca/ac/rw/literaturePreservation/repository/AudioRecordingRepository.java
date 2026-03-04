package auca.ac.rw.literaturePreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.rw.literaturePreservation.domain.AudioRecording;

public interface AudioRecordingRepository extends JpaRepository<AudioRecording, Long> {

}
