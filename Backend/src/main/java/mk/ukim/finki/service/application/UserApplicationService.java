package mk.ukim.finki.service.application;


import mk.ukim.finki.dto.LoginResponseDto;
import mk.ukim.finki.dto.LoginUserDto;
import mk.ukim.finki.dto.create.CreateUserDto;
import mk.ukim.finki.dto.display.DisplayAccommodationDto;
import mk.ukim.finki.dto.display.DisplayUserDto;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
    List<DisplayAccommodationDto> addAccommodationToTempList(String username, Long accommodationId);
    List<DisplayAccommodationDto> getUserTempList(String username);
    List<DisplayAccommodationDto> bookAnAccommodationFromTempList(String username);
}
