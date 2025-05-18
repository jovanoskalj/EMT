package mk.ukim.finki.service.domain;

import mk.ukim.finki.dto.CategoryCountDTO;
import mk.ukim.finki.model.DisplayAccommodationsByHost;
import mk.ukim.finki.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccomodationService {

    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> save(Accommodation accommodation);

    void deleteById(Long id);
    Optional<Accommodation> rentRoom(Long id);
     List<CategoryCountDTO> countByCategory();
    List<DisplayAccommodationsByHost> getAccommodationStatsPerHost();
    Optional<Accommodation> details(Long id);

}
