package mk.ukim.finki.dto.create;


import mk.ukim.finki.model.domain.User;
import mk.ukim.finki.model.enumerations.Role;

public record CreateUserDto(String username,
                            String password,
                            String repeatPassword,
                            String name,
                            String surname,
                            Role role) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
