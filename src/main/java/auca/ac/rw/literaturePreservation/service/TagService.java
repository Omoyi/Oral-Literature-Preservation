package auca.ac.rw.literaturePreservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import auca.ac.rw.literaturePreservation.domain.Tag;
import auca.ac.rw.literaturePreservation.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag saveTag(Tag tag) {
        if (tag.getName() == null || tag.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag name is required");
        }
        if (tagRepository.existsByName(tag.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag already exists: " + tag.getName());
        }
        return tagRepository.save(tag);
    }

    public Tag getTagByName(String name) {
        return tagRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found: " + name));
    }
}
