package mk.ukim.finki.service.application;

import mk.ukim.finki.dto.create.CreateAvailabilityDto;
import mk.ukim.finki.dto.display.DisplayAvailabilityDto;

import java.util.List;
import java.util.Optional;

public interface ApplicationAvailabilityService {
    List<DisplayAvailabilityDto> findAll();

    Optional<DisplayAvailabilityDto> findById(Long id);

    Optional<DisplayAvailabilityDto> update(Long id, CreateAvailabilityDto availability);

    Optional<DisplayAvailabilityDto> save(CreateAvailabilityDto availability);

    void deleteById(Long id);
    List<DisplayAvailabilityDto> findByAccommodationId(Long accommodationId);
}
