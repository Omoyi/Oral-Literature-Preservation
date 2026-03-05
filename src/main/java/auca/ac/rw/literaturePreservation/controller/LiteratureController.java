package auca.ac.rw.literaturePreservation.controller;

import auca.ac.rw.literaturePreservation.domain.EGenreType;
import auca.ac.rw.literaturePreservation.domain.Literature;
import auca.ac.rw.literaturePreservation.service.LiteratureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/literature")
public class LiteratureController {

    private final LiteratureService literatureService;

    public LiteratureController(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    // Simple request payload (cleaner than embedding storyteller object)
    //A DTO(Data Transfer Object) is a lightweight container used to carry data from the client 
    // to the server or from the server to the client.
    public static class LiteratureRequest {
        public String title;
        public String summary;
        public String language;
        public LocalDate recordedDate;
        public Long storytellerId;
    }

    @PostMapping("/save")
    public Literature saveLiterature(@RequestBody LiteratureRequest request) {

        Literature literature = new Literature();
        literature.setTitle(request.title);
        literature.setSummary(request.summary);
        literature.setLanguage(request.language);
        literature.setRecordedDate(request.recordedDate);

        return literatureService.saveLiterature(literature, request.storytellerId);
    }

    // Pagination + sorting:
    // Example: /api/literature/all?page=0&size=5&sort=title,asc
    @GetMapping("/all")
    public Page<Literature> getAllLiterature(@PageableDefault(size = 5) Pageable pageable) {
        return literatureService.getAllLiterature(pageable);
    }

    // Example: /api/literature/by-storyteller/1?page=0&size=5&sort=recordedDate,desc
    @GetMapping("/by-storyteller/{storytellerId}")
    public Page<Literature> getByStoryteller(@PathVariable Long storytellerId,
                                             @PageableDefault(size = 5) Pageable pageable) {
        return literatureService.getLiteratureByStoryteller(storytellerId, pageable);
    }

    @GetMapping("/{id}")
    public Literature getLiteratureById(@PathVariable Long id) {
        return literatureService.getLiteratureById(id);
    }

    @PostMapping("/{literatureId}/add-tag/{tagId}")
    public Literature addTagToLiterature(@PathVariable Long literatureId, @PathVariable Long tagId) {
        return literatureService.addTagToLiterature(literatureId, tagId);
    }

    @GetMapping("/by-genre/{genre}")
    public Page<Literature> getByGenre(@PathVariable String genre, Pageable pageable) {
        try {
            EGenreType g = EGenreType.valueOf(genre.toUpperCase());
            return literatureService.getLiteratureByGenre(g, pageable);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid genre: " + genre + ". Use values like: " + java.util.Arrays.toString(EGenreType.values())
            );
        }
    }
}