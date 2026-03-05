package auca.ac.rw.literaturePreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.literaturePreservation.domain.AudioRecording;
import auca.ac.rw.literaturePreservation.service.AudioRecordingService;

@RestController
@RequestMapping("/api/audio")
public class AudioRecordingController {

    @Autowired
    private AudioRecordingService audioRecordingService;

    @PostMapping("/add/{literatureId}")
    public AudioRecording addRecording(@PathVariable Long literatureId,
                                       @RequestBody AudioRecording recording) {
        return audioRecordingService.addRecording(literatureId, recording);
    }

    @GetMapping("/by-literature/{literatureId}")
    public AudioRecording getByLiterature(@PathVariable Long literatureId) {
        return audioRecordingService.getByLiteratureId(literatureId);
    }
}