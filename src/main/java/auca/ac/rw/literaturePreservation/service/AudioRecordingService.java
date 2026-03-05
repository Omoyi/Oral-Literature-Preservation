package auca.ac.rw.literaturePreservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import auca.ac.rw.literaturePreservation.domain.AudioRecording;
import auca.ac.rw.literaturePreservation.domain.Literature;
import auca.ac.rw.literaturePreservation.repository.AudioRecordingRepository;
import auca.ac.rw.literaturePreservation.repository.LiteratureRepository;

@Service
public class AudioRecordingService {

    @Autowired
    private AudioRecordingRepository audioRecordingRepository;

    @Autowired
    private LiteratureRepository literatureRepository;

    public AudioRecording addRecording(Long literatureId, AudioRecording recording) {

        Literature literature = literatureRepository.findById(literatureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Literature not found with id: " + literatureId));

        if (audioRecordingRepository.existsByLiteratureId(literatureId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This literature already has an audio recording");
        }

        if (recording.getFileUrl() == null || recording.getFileUrl().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "fileUrl is required");
        }

        recording.setLiterature(literature);
        return audioRecordingRepository.save(recording);
    }

    public AudioRecording getByLiteratureId(Long literatureId) {
        return audioRecordingRepository.findByLiteratureId(literatureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Audio recording not found for literature id: " + literatureId));
    }
}
