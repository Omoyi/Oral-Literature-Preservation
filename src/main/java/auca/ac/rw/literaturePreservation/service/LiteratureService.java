package auca.ac.rw.literaturePreservation.service;

import auca.ac.rw.literaturePreservation.domain.Literature;
import auca.ac.rw.literaturePreservation.domain.Storyteller;
import auca.ac.rw.literaturePreservation.repository.LiteratureRepository;
import auca.ac.rw.literaturePreservation.repository.StorytellerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LiteratureService {

    private final LiteratureRepository literatureRepository;
    private final StorytellerRepository storytellerRepository;

    public LiteratureService(LiteratureRepository literatureRepository,
                             StorytellerRepository storytellerRepository) {
        this.literatureRepository = literatureRepository;
        this.storytellerRepository = storytellerRepository;
    }

    public Literature saveLiterature(Literature literature, Long storytellerId) {

        if (literature.getTitle() == null || literature.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
        }

        if (literatureRepository.existsByTitle(literature.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Literature with this title already exists: " + literature.getTitle());
        }

        Storyteller storyteller = storytellerRepository.findById(storytellerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Storyteller not found with id: " + storytellerId));

        literature.setStoryteller(storyteller);

        return literatureRepository.save(literature);
    }

    public Literature getLiteratureById(Long id) {
        return literatureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Literature not found with id: " + id));
    }

    public Page<Literature> getAllLiterature(Pageable pageable) {
        return literatureRepository.findAll(pageable);
    }

    public Page<Literature> getLiteratureByStoryteller(Long storytellerId, Pageable pageable) {
        Storyteller storyteller = storytellerRepository.findById(storytellerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Storyteller not found with id: " + storytellerId));

        return literatureRepository.findByStorytellerId(storytellerId, pageable);
    }
}