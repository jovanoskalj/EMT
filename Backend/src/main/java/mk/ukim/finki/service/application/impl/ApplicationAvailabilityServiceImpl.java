package mk.ukim.finki.service.application.impl;

import mk.ukim.finki.dto.create.CreateAvailabilityDto;
import mk.ukim.finki.dto.display.DisplayAvailabilityDto;
import mk.ukim.finki.service.application.ApplicationAvailabilityService;
import mk.ukim.finki.service.domain.AvailabilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationAvailabilityServiceImpl implements ApplicationAvailabilityService {

    private final AvailabilityService availabilityService;

    public ApplicationAvailabilityServiceImpl(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @Override
    public List<DisplayAvailabilityDto> findAll() {
        return availabilityService.findAll().stream()
                .map(DisplayAvailabilityDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAvailabilityDto> findById(Long id) {
        return availabilityService.findById(id).map(DisplayAvailabilityDto::from);
    }

    @Override
    public Optional<DisplayAvailabilityDto> update(Long id, CreateAvailabilityDto availability) {
        return availabilityService.update(id,availability.toAvailability())
                .map(DisplayAvailabilityDto::from);    }

    @Override
    public Optional<DisplayAvailabilityDto> save(CreateAvailabilityDto availability) {
        return availabilityService.save(availability.toAvailability())
                .map(DisplayAvailabilityDto::from);
    }

    @Override
    public void deleteById(Long id) {
        availabilityService.deleteById(id);

    }

    @Override
    public List<DisplayAvailabilityDto> findByAccommodationId(Long accommodationId) {
        return availabilityService.findByAccommodationId(accommodationId)
                .stream()
                .map(DisplayAvailabilityDto::from)
                .collect(Collectors.toList());
    }
}
