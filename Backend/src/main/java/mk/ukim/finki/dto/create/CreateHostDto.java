package mk.ukim.finki.dto.create;

import mk.ukim.finki.model.domain.Country;
import mk.ukim.finki.model.domain.Host;

public record CreateHostDto(String name, String surname, Long countryId) {

}
