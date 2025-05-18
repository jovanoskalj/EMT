package mk.ukim.finki.service.domain.impl;

import mk.ukim.finki.model.domain.Host;
import mk.ukim.finki.model.projections.HostByCountry;
import mk.ukim.finki.model.projections.HostNameProjection;
import mk.ukim.finki.repository.HostRepository;
import mk.ukim.finki.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }


    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return Optional.of(hostRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id)
                .map(existingAccommodation -> {
                    if (host.getName() != null) {
                        existingAccommodation.setName(host.getName());
                    }
                    if (host.getSurname() != null ) {
                        existingAccommodation.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null ) {
                        existingAccommodation.setCountry(host.getCountry());
                    }
                    return hostRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Host> save(Host host) {

        if (host.getName()!=null && host.getCountry()!=null && host.getSurname()!=null){
            return Optional.of(hostRepository.save(new Host(
                    host.getName(),
                    host.getSurname(),
                    host.getCountry()
            )));
        }


        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public List<HostByCountry> getHostCountByCountry() {
        return hostRepository.getHostCountByCountry();
    }

    @Override
    public List<HostNameProjection> getAllHostNames() {
        return hostRepository.findAllHostNames();
    }

}
