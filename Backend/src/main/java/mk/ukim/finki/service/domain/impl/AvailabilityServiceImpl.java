package mk.ukim.finki.service.domain.impl;

import mk.ukim.finki.model.domain.Availability;
import mk.ukim.finki.repository.AvailabilityRepository;
import mk.ukim.finki.service.domain.AccomodationService;
import mk.ukim.finki.service.domain.AvailabilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final AccomodationService accomodationService;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, AccomodationService accomodationService) {
        this.availabilityRepository = availabilityRepository;
        this.accomodationService = accomodationService;
    }

    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Optional<Availability> findById(Long id) {
        return Optional.of(availabilityRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<Availability> update(Long id, Availability availability) {

        return availabilityRepository.findById(id)
                .map(existingAccommodation -> {
                    if (availability.getDateFrom() != null) {
                        existingAccommodation.setDateFrom(availability.getDateFrom());
                    }
                    if (availability.getDateTo() != null ) {
                        existingAccommodation.setDateTo(availability.getDateTo());
                    }
                    if (availability.getAccommodationId() != null ) {
                        existingAccommodation.setAccommodationId(availability.getAccommodationId());
                    }
                    if (availability.getPrice() != null ) {
                        existingAccommodation.setPrice(availability.getPrice());
                    }
                    return availabilityRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Availability> save(Availability availability) {
        if (availability.getDateFrom()!=null
                && availability.getDateTo()!=null
                && accomodationService.findById(availability.getAccommodationId()).isPresent()
                && availability.getPrice()!=null) {
            return Optional.of(availabilityRepository.save(
                    new Availability(
                            availability.getDateFrom(),
                            availability.getDateTo(),
                            availability.getAccommodationId(),
                            availability.getPrice())));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    public List<Availability> findByAccommodationId(Long accommodationId) {
        return availabilityRepository.findByAccommodationId(accommodationId);
    }
}
