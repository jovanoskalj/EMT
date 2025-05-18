package mk.ukim.finki.dto.display;

import mk.ukim.finki.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDto(String name, String continent, Long id) {
    public static DisplayCountryDto from (Country country){
        return new DisplayCountryDto(

                country.getName(),
                country.getContinent(),
                country.getId());
    }
    public static List<DisplayCountryDto> from(List<Country> countries){
        return countries.stream().map(DisplayCountryDto::from).collect(Collectors.toList());
    }
}
