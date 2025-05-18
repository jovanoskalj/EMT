package mk.ukim.finki.dto.create;

import mk.ukim.finki.model.domain.Accommodation;
import mk.ukim.finki.model.domain.Host;
import mk.ukim.finki.model.enumerations.CategoryAcc;

public record CreateAccommodationDto(String name, CategoryAcc category, Integer numRooms, Host host) {
    public Accommodation toAccommodation(){
        return new Accommodation( name,  category,  numRooms,  host);
    }
}
