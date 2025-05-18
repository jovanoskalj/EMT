package mk.ukim.finki.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.model.domain.User;
import mk.ukim.finki.model.enumerations.Role;
import mk.ukim.finki.repository.CountryRepository;
import mk.ukim.finki.repository.HostRepository;
import mk.ukim.finki.repository.UserRepository;
import mk.ukim.finki.web.AccommodationController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationController accommodationController;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AccommodationController accommodationController, HostRepository hostRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationController = accommodationController;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void init() {
//        Country c1=countryRepository.save(new Country("Macedonia","Europe"));
//        Country c2= countryRepository.save(new Country("Japan","Asia"));
//
//        Host h1= hostRepository.save(new Host("Dimitar","Iliev",c1));
//        Host h2=hostRepository.save(new Host("Mila","Ilieva",c2));
//
//        accommodationController.save(new CreateAccommodationDto("Family", CategoryAcc.APARTMENT,4,h1));
//        accommodationController.save(new CreateAccommodationDto("Solo", CategoryAcc.FLAT,1,h2));
//

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "User",
                "User",
                Role.ROLE_USER
        ));

        userRepository.save(new User(
                "ek",
                passwordEncoder.encode("ek"),
                "host name",
                "host surname",
                Role.ROLE_HOST
        ));
    }

}
