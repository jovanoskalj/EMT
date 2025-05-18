package mk.ukim.finki.service.domain;

import mk.ukim.finki.model.domain.Host;
import mk.ukim.finki.model.projections.HostByCountry;
import mk.ukim.finki.model.projections.HostNameProjection;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> update(Long id, Host host);

    Optional<Host> save(Host host);

    void deleteById(Long id);
    List<HostByCountry> getHostCountByCountry();
    List<HostNameProjection> getAllHostNames();


}
