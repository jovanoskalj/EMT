package mk.ukim.finki.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.dto.CategoryCountDTO;
import mk.ukim.finki.dto.create.CreateAccommodationDto;
import mk.ukim.finki.dto.display.DisplayAccommodationDto;
import mk.ukim.finki.dto.display.DisplayAccommodationsByHostDto;
import mk.ukim.finki.service.application.ApplicationAccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodations")
@Tag(name = "Accommodations API", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final ApplicationAccommodationService accommodationService;

    public AccommodationController(ApplicationAccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Operation(summary = "Find all", description = "Shows the list of accommodations")
    @GetMapping
    public List<DisplayAccommodationDto> findAll(){
        return accommodationService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new accommodation",
            description = "Creates a new accommodation based on the given AccommodationDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto accommodation){
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Update an existing accommodation", description = "Updates an accommodation by ID using AccommodationDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation) {
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete an accommodation", description = "Deletes an accommodation by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Rent accommodation", description = "Rents an accommodation.")

    @PutMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDto> rent(@PathVariable Long id) {
        return accommodationService.rentRoom(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/countByCategory")
    public List<CategoryCountDTO> getAccommodationCategoryStats() {
        return accommodationService.countByCategoryAcc();
    }

    @GetMapping("/by-host")
    public List<DisplayAccommodationsByHostDto> getStatsByHost() {
        return accommodationService.getAccommodationStatsPerHost();
    }


    @GetMapping("{id}/details")
    public ResponseEntity<DisplayAccommodationDto> details(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
