package mk.ukim.finki.dto.create;

import mk.ukim.finki.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}
