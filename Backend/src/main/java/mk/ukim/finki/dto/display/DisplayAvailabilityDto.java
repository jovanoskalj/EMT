package mk.ukim.finki.dto.display;

import mk.ukim.finki.model.domain.Availability;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayAvailabilityDto(Long id, LocalDateTime dateFrom, LocalDateTime dateTo, Long accommodationId, Double price) {
    public static DisplayAvailabilityDto from (Availability availability){
        return new DisplayAvailabilityDto(availability.getId(),
                availability.getDateFrom(),
                availability.getDateTo(),
                availability.getAccommodationId(),
                availability.getPrice());
    }
    public static List<DisplayAvailabilityDto> from(List<Availability> availabilityList){
        return availabilityList.stream().map(DisplayAvailabilityDto::from).collect(Collectors.toList());
    }
}
