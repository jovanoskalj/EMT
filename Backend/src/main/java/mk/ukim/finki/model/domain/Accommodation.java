package mk.ukim.finki.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.model.enumerations.CategoryAcc;

@Entity
@Data
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryAcc category;
    private Integer numRooms;
    @ManyToOne
    private Host host;

    public Accommodation(String name, CategoryAcc category, Integer numRooms, Host host) {
        this.name = name;
        this.category = category;
        this.numRooms = numRooms;
        this.host = host;
    }

    public Accommodation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryAcc getCategory() {
        return category;
    }

    public void setCategory(CategoryAcc category) {
        this.category = category;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
