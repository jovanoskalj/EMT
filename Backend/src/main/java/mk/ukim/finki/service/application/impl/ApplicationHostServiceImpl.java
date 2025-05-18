package mk.ukim.finki.service.application.impl;

import mk.ukim.finki.dto.create.CreateHostDto;
import mk.ukim.finki.dto.display.DisplayHostDto;
import mk.ukim.finki.events.HostChangedEvent;
import mk.ukim.finki.model.domain.Country;
import mk.ukim.finki.model.domain.Host;
import mk.ukim.finki.model.projections.HostByCountry;
import mk.ukim.finki.model.projections.HostNameProjection;
import mk.ukim.finki.repository.CountryRepository;
import mk.ukim.finki.repository.HostRepository;
import mk.ukim.finki.service.application.ApplicationHostService;
import mk.ukim.finki.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationHostServiceImpl implements ApplicationHostService {

    private final HostService hostService;
    private final ApplicationEventPublisher eventPublisher;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public ApplicationHostServiceImpl(
            HostService hostService,
            ApplicationEventPublisher eventPublisher,
            CountryRepository countryRepository,
            HostRepository hostRepository
    ) {
        this.hostService = hostService;
        this.eventPublisher = eventPublisher;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream()
                .map(DisplayHostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id)
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto) {
        Country country = countryRepository.findById(hostDto.countryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        Host updatedHost = new Host(hostDto.name(), hostDto.surname(), country);

        Optional<Host> updated = hostService.update(id, updatedHost);

        updated.ifPresent(host -> eventPublisher.publishEvent(new HostChangedEvent(this)));

        return updated.map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto hostDto) {
        Country country = countryRepository.findById(hostDto.countryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        Host host = new Host(hostDto.name(), hostDto.surname(), country);
        hostRepository.save(host);

        return Optional.of(DisplayHostDto.from(host));
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
        eventPublisher.publishEvent(new HostChangedEvent(this));
    }

    @Override
    public List<HostByCountry> getHostCountByCountry() {
        return hostService.getHostCountByCountry();
    }

    @Override
    public List<HostNameProjection> getAllHostNames() {
        return hostService.getAllHostNames();
    }
}
