package auca.ac.rw.literaturePreservation.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.literaturePreservation.domain.*;
import auca.ac.rw.literaturePreservation.repository.*;

@Service
public class StorytellerService {

    @Autowired
    private StorytellerRepository storytellerRepository;
    @Autowired
    private LocationService locationService;

    public Storyteller saveStoryteller(Storyteller storyteller) {
        if (storytellerRepository.existsByEmail(storyteller.getEmail())) {
            throw new RuntimeException("Email already exists: " + storyteller.getEmail());
        }

        // Ensure location exists
        Location savedLocation = locationService.getLocationById(storyteller.getLocation().getLocation_Id());
        storyteller.setLocation(savedLocation);

        return storytellerRepository.save(storyteller);
    }

    public Storyteller getStorytellerById(Long id) {
        return storytellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storyteller not found with id: " + id));
    }

    public List<Storyteller> getAllStorytellers() {
        return storytellerRepository.findAll();
    }

    public List<Storyteller> getStorytellersByLocation(Long locationId) {
        return storytellerRepository.findByLocation_Location_id(locationId);
    }
}
