package mk.ukim.finki.dto.create;

import mk.ukim.finki.model.domain.Availability;

import java.time.LocalDateTime;

public record CreateAvailabilityDto(LocalDateTime dateFrom, LocalDateTime dateTo, Long accommodationId, Double price) {
    public Availability toAvailability(){
        return new Availability(dateFrom,dateTo,accommodationId,price);
    }
}
