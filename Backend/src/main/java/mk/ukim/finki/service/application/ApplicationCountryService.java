package mk.ukim.finki.service.application;

import mk.ukim.finki.dto.create.CreateCountryDto;
import mk.ukim.finki.dto.display.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface ApplicationCountryService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    void deleteById(Long id);
}
