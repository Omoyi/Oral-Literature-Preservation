package auca.ac.rw.literaturePreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.literaturePreservation.domain.ELocationType;
import auca.ac.rw.literaturePreservation.domain.Location;
import auca.ac.rw.literaturePreservation.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location saveLocation(Location location) {
        if (location.getCode() != null && locationRepository.existsByCode(location.getCode())) {
            throw new RuntimeException("Location code already exists: " + location.getCode());
        }
        return locationRepository.save(location);
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
    }

    public Location getProvinceByCode(String provinceCode) {
        Location province = locationRepository.findByCode(provinceCode)
                .orElseThrow(() -> new RuntimeException("Province not found with code: " + provinceCode));

        if (province.getType() != ELocationType.PROVINCE) {
            throw new RuntimeException("Location with code " + provinceCode + " is not a PROVINCE");
        }
        return province;
    }

    public Location getProvinceByName(String provinceName) {
        return locationRepository.findByNameAndType(provinceName, ELocationType.PROVINCE)
                .orElseThrow(() -> new RuntimeException("Province not found with name: " + provinceName));
    }

    public List<Location> getChildren(Long parentId) {
        return locationRepository.findByParent_Location_id(parentId);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
