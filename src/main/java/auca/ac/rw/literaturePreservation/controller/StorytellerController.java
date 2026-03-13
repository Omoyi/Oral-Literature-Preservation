package auca.ac.rw.literaturePreservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.literaturePreservation.domain.Storyteller;
import auca.ac.rw.literaturePreservation.service.StorytellerService;

@RestController
@RequestMapping("/api/storytellers")
public class StorytellerController {
    
    @Autowired
    private StorytellerService storytellerService;

    @PostMapping("/save")
    public Storyteller saveStoryteller(@RequestBody Storyteller storyteller) {
        return storytellerService.saveStoryteller(storyteller);
    }

    @GetMapping("/all")
    public List<Storyteller> getAllStorytellers() {
        return storytellerService.getAllStorytellers();
    }

    @GetMapping("/{id}")
    public Storyteller getStorytellerById(@PathVariable Long id) {
        return storytellerService.getStorytellerById(id);
    }

    @GetMapping("/by-province/code/{code}")
    public List<Storyteller> getByProvinceCode(@PathVariable String code) {
        return storytellerService.getStorytellersByProvinceCode(code);
    }
    
    @GetMapping("/by-province/name/{name}")
    public List<Storyteller> getByProvinceName(@PathVariable String name) {
        return storytellerService.getStorytellersByProvinceName(name);
    }
}
