package mk.ukim.finki.dto.display;

import mk.ukim.finki.model.domain.Country;
import mk.ukim.finki.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(String name, String surname, Country country, Long id) {
    public static DisplayHostDto from (Host host){
        return new DisplayHostDto(
                host.getName(),
                host.getSurname(),
                host.getCountry(),
                host.getId());
    }
    public static List<DisplayHostDto> from(List<Host> hosts){
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }
}
