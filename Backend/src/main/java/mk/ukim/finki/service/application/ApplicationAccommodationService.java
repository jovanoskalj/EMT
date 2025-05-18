package mk.ukim.finki.service.application;

import mk.ukim.finki.dto.CategoryCountDTO;
import mk.ukim.finki.dto.create.CreateAccommodationDto;
import mk.ukim.finki.dto.display.DisplayAccommodationDto;
import mk.ukim.finki.dto.display.DisplayAccommodationsByHostDto;

import java.util.List;
import java.util.Optional;

public interface ApplicationAccommodationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);

    void deleteById(Long id);
    Optional<DisplayAccommodationDto> rentRoom(Long id);
    List<CategoryCountDTO> countByCategoryAcc();
    List<DisplayAccommodationsByHostDto> getAccommodationStatsPerHost();
    Optional<DisplayAccommodationDto> details(Long id);

}
