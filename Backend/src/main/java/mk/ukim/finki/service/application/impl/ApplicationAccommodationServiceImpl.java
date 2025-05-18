package mk.ukim.finki.service.application.impl;

import mk.ukim.finki.dto.CategoryCountDTO;
import mk.ukim.finki.dto.create.CreateAccommodationDto;
import mk.ukim.finki.dto.display.DisplayAccommodationDto;
import mk.ukim.finki.dto.display.DisplayAccommodationsByHostDto;
import mk.ukim.finki.service.application.ApplicationAccommodationService;
import mk.ukim.finki.service.domain.AccomodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationAccommodationServiceImpl implements ApplicationAccommodationService {

    private final AccomodationService accomodationService;

    public ApplicationAccommodationServiceImpl(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accomodationService.findAll().stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accomodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        return accomodationService.update(id,accommodation.toAccommodation())
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        return accomodationService.save(accommodation.toAccommodation())
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accomodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> rentRoom(Long id) {
        return accomodationService.rentRoom(id)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public List<CategoryCountDTO> countByCategoryAcc() {
        return accomodationService.countByCategory();
    }



    @Override
    public List<DisplayAccommodationsByHostDto> getAccommodationStatsPerHost() {
        return accomodationService.getAccommodationStatsPerHost().stream()
                .map(accommodation -> new DisplayAccommodationsByHostDto(
                        accommodation.getName(),
                        accommodation.getSurname(),
                        accommodation.getNumAccommodations()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAccommodationDto> details(Long id) {
        return accomodationService.findById(id).map(DisplayAccommodationDto::from);

    }

}
