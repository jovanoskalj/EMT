package mk.ukim.finki.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.dto.create.CreateAvailabilityDto;
import mk.ukim.finki.dto.display.DisplayAvailabilityDto;
import mk.ukim.finki.service.application.ApplicationAvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/availability")
@Tag(
        name = "Availability API",
        description = "Endpoints for managing availability dates of accommodations"
)
public class AvailabilityController {
    private final ApplicationAvailabilityService availabilityService;

    public AvailabilityController(ApplicationAvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }
    @Operation(
            summary = "Find all availabilities",
            description = "Returns a list of all availability periods for all accommodations."
    )

    @GetMapping

    public List<DisplayAvailabilityDto> findAll() {
        return availabilityService.findAll();
    }

    @Operation(
            summary = "Find availability by ID",
            description = "Returns the availability details for a specific ID."
    )

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAvailabilityDto> findById(@PathVariable Long id) {
        return availabilityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add new availability",
            description = "Creates a new availability period for an accommodation."
    )

    @PostMapping("/add")
    public ResponseEntity<DisplayAvailabilityDto> save(@RequestBody CreateAvailabilityDto availability) {
        return availabilityService.save(availability)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update availability",
            description = "Updates an existing availability period with new dates or accommodation."
    )

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAvailabilityDto> update(@PathVariable Long id, @RequestBody CreateAvailabilityDto availability) {
        return availabilityService.update(id, availability)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Delete availability",
            description = "Deletes the availability period with the specified ID."
    )

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (availabilityService.findById(id).isPresent()) {
            availabilityService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Get available dates for accommodation",
            description = "Returns a list of available check-in and check-out dates for a given accommodation."
    )

    @GetMapping("/availableDates/{accommodationId}")
    public List<String> getAvailableDates(@PathVariable Long accommodationId) {
        return availabilityService.findByAccommodationId(accommodationId)
                .stream()
                .map(a -> "Check in: " + a.dateFrom() + ", Check out: " + a.dateTo())
                .collect(Collectors.toList());
    }
}
