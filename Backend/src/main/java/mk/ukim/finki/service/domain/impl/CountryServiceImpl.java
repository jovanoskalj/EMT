package mk.ukim.finki.service.domain.impl;

import mk.ukim.finki.model.domain.Country;
import mk.ukim.finki.repository.CountryRepository;
import mk.ukim.finki.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(countryRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id)
                .map(existingAccommodation -> {
                    if (country.getName() != null) {
                        existingAccommodation.setName(country.getName());
                    }
                    if (country.getContinent() != null ) {
                        existingAccommodation.setContinent(country.getContinent());
                    }
                    return countryRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Country> save(Country country) {

        if (country.getName()!=null && country.getContinent()!=null){
            return Optional.of(countryRepository.save(new Country(
                    country.getName(),
                    country.getContinent()
            )));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
