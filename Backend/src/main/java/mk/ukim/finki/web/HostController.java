package mk.ukim.finki.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.dto.create.CreateHostDto;
import mk.ukim.finki.dto.display.DisplayHostDto;
import mk.ukim.finki.model.projections.HostByCountry;
import mk.ukim.finki.model.projections.HostNameProjection;
import mk.ukim.finki.repository.HostRepository;
import mk.ukim.finki.service.application.ApplicationHostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hosts")
@Tag(
        name = "Host API",
        description = "Endpoints for managing hosts"
)
public class HostController {
    private final ApplicationHostService hostService;
    private final HostRepository hostRepository;

    public HostController(ApplicationHostService hostService, HostRepository hostRepository) {
        this.hostService = hostService;
        this.hostRepository = hostRepository;
    }

    @Operation(
            summary = "Find all hosts",
            description = "Returns a list of all hosts."
    )
    @GetMapping
    public List<DisplayHostDto> findAll(){
        return hostService.findAll();
    }

    @Operation(
            summary = "Find host by ID",
            description = "Returns the host with the specified ID."
    )
    @GetMapping("{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new host",
            description = "Creates a new host using the provided data."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto host){
        return hostService.save(host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a host",
            description = "Updates an existing host with the given ID."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto host) {
        return hostService.update(id, host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a host",
            description = "Deletes the host with the given ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-country")
    public List<HostByCountry> getHostCountByCountry() {
        return hostService.getHostCountByCountry();
    }

    @GetMapping("/api/hosts/names")
    public List<HostNameProjection> getAllHostNames() {
        return hostService.getAllHostNames();
    }

}
