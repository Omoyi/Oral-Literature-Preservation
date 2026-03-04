package auca.ac.rw.literaturePreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.literaturePreservation.domain.Tag;
import auca.ac.rw.literaturePreservation.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/save")
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }
}
