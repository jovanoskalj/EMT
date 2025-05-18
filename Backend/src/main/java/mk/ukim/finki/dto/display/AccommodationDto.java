package mk.ukim.finki.dto.display;

import mk.ukim.finki.model.domain.Accommodation;
import mk.ukim.finki.model.enumerations.CategoryAcc;

import java.util.List;
import java.util.stream.Collectors;

public record AccommodationDto(String name, CategoryAcc category, Integer numRooms, Long hostId) {
    public static AccommodationDto from (Accommodation accommodation){
        return new AccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getNumRooms(),
                accommodation.getHost().getId());
    }
    public static List<AccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(AccommodationDto::from).collect(Collectors.toList());
    }
}