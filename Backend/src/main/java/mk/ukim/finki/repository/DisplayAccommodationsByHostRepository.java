package mk.ukim.finki.repository;

import mk.ukim.finki.model.DisplayAccommodationsByHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisplayAccommodationsByHostRepository extends JpaRepository<DisplayAccommodationsByHost, String> {
    List<DisplayAccommodationsByHost> findAll();
}
