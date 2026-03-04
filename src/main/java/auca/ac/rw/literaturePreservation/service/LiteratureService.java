package auca.ac.rw.literaturePreservation.service;

import auca.ac.rw.literaturePreservation.domain.Literature;
import auca.ac.rw.literaturePreservation.domain.Storyteller;
import auca.ac.rw.literaturePreservation.domain.Tag;
import auca.ac.rw.literaturePreservation.repository.LiteratureRepository;
import auca.ac.rw.literaturePreservation.repository.StorytellerRepository;
import auca.ac.rw.literaturePreservation.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LiteratureService {

    @Autowired
    private LiteratureRepository literatureRepository;

    @Autowired
    private StorytellerRepository storytellerRepository;

    @Autowired
    private TagRepository tagRepository;

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

    public Literature addTagToLiterature(Long literatureId, Long tagId) {

        Literature literature = literatureRepository.findById(literatureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Literature not found with id: " + literatureId));
    
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tag not found with id: " + tagId));
    
        literature.getTags().add(tag);
    
        return literatureRepository.save(literature);
    }
}