package mk.ukim.finki.repository;

import mk.ukim.finki.model.domain.Host;
import mk.ukim.finki.model.projections.HostByCountry;
import mk.ukim.finki.model.projections.HostNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface HostRepository extends JpaRepository<Host, Long> {


    @Query(value = "SELECT country.name AS country, COUNT(host.id) AS hostCount " +
            "FROM host " +
            "JOIN country ON host.country_id = country.id " +
            "GROUP BY country.name",
            nativeQuery = true)
    List<HostByCountry> getHostCountByCountry();
    @Query("SELECT h.name AS firstName, h.surname AS lastName FROM Host h")
    List<HostNameProjection> findAllHostNames();

}
