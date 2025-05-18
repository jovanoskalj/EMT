package mk.ukim.finki.repository;

import mk.ukim.finki.model.domain.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByAccommodationId(Long accommodationId);

}
