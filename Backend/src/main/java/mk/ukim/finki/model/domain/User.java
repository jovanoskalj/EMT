package mk.ukim.finki.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.model.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "shop_users")
@NamedEntityGraph(
        name = "User.withoutTemporaryReservations",
        attributeNodes = {
                @NamedAttributeNode("username"),
                @NamedAttributeNode("name"),
                @NamedAttributeNode("surname"),
                @NamedAttributeNode("isAccountNonExpired"),
                @NamedAttributeNode("isAccountNonLocked"),
                @NamedAttributeNode("isCredentialsNonExpired"),
                @NamedAttributeNode("isEnabled"),
                @NamedAttributeNode("role")
        }
)
public class User implements UserDetails {
    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    @ManyToMany(fetch = FetchType.LAZY)
    private List<Accommodation> temporaryReservations;
    public User() {
        this.temporaryReservations = new ArrayList<>();

    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.temporaryReservations = new ArrayList<>();
    }
    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.temporaryReservations = new ArrayList<>();

    }
    public User(UserDetails userDetails){
        this.username=userDetails.getUsername();
        this.password=userDetails.getPassword();
        this.temporaryReservations = new ArrayList<>();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    public List<Accommodation> getTemporaryReservations() {
        return temporaryReservations;
    }
}