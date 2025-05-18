package mk.ukim.finki.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "display_accommodations_by_host")
public class DisplayAccommodationsByHost {

    @Id
    private String name;

    private String surname;

    @Column(name = "num_accommodations")
    private int numAccommodations;

    public DisplayAccommodationsByHost() {
    }

    public DisplayAccommodationsByHost(String name, String surname, int numAccommodations) {
        this.name = name;
        this.surname = surname;
        this.numAccommodations = numAccommodations;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getNumAccommodations() {
        return numAccommodations;
    }
}
