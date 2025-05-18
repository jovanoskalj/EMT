package mk.ukim.finki.dto.display;

import mk.ukim.finki.model.domain.Accommodation;
import mk.ukim.finki.model.domain.Host;
import mk.ukim.finki.model.enumerations.CategoryAcc;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(String name, CategoryAcc category, Integer numRooms, Host host, Long id) {
    public static DisplayAccommodationDto from (Accommodation accommodation){
        return new DisplayAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getNumRooms(),
                accommodation.getHost(),
                accommodation.getId()
        );
    }
    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }
}
