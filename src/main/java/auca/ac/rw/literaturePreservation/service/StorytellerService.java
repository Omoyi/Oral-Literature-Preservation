package auca.ac.rw.literaturePreservation.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import auca.ac.rw.literaturePreservation.domain.*;
import auca.ac.rw.literaturePreservation.repository.*;

@Service
public class StorytellerService {

    @Autowired
    private StorytellerRepository storytellerRepository;
    @Autowired
    private LocationService locationService;

    public Storyteller saveStoryteller(Storyteller storyteller) {
        // if (storytellerRepository.existsByEmail(storyteller.getEmail())) {
        //     throw new RuntimeException("Email already exists: " + storyteller.getEmail());
        // }

        // // Ensure location exists
        // Location savedLocation = locationService.getLocationById(storyteller.getLocation().getId());
        // storyteller.setLocation(savedLocation);

        // return storytellerRepository.save(storyteller);

        if (storyteller.getEmail() == null || storyteller.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        if (storytellerRepository.existsByEmail(storyteller.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists: " + storyteller.getEmail());
        }
    
        if (storyteller.getLocation() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location id is required (use: location: {\"id\": <villageId>})");
        }
    
        Location savedLocation = locationService.getLocationById(storyteller.getLocation().getId());
    
        if (savedLocation.getType() != ELocationType.VILLAGE) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Storyteller location must be a VILLAGE. Provided: " + savedLocation.getType()
            );
        }
    
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
        Location loc = locationService.getLocationById(locationId);
        return storytellerRepository.findByLocation(loc);
    }

    public List<Storyteller> getStorytellersByProvinceCode(String provinceCode) {
        Location province = locationService.getProvinceByCode(provinceCode);
    
        if (province.getType() != ELocationType.PROVINCE) {
            throw new RuntimeException("Provided code does not belong to a province");
        }
    
        List<Location> provinceLocations = new ArrayList<>();
        provinceLocations.add(province);
        provinceLocations.addAll(locationService.getAllDescendants(province));
    
        return storytellerRepository.findByLocationIn(provinceLocations);
    }
    
    public List<Storyteller> getStorytellersByProvinceName(String provinceName) {
        Location province = locationService.getProvinceByName(provinceName);
    
        if (province.getType() != ELocationType.PROVINCE) {
            throw new RuntimeException("Provided name does not belong to a province");
        }
    
        List<Location> provinceLocations = new ArrayList<>();
        provinceLocations.add(province);
        provinceLocations.addAll(locationService.getAllDescendants(province));
    
        return storytellerRepository.findByLocationIn(provinceLocations);
    }
}
