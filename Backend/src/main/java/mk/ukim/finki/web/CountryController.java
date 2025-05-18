package mk.ukim.finki.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.dto.create.CreateCountryDto;
import mk.ukim.finki.dto.display.DisplayCountryDto;
import mk.ukim.finki.service.application.ApplicationCountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@Tag(
        name = "Country API",
        description = "Endpoints for managing countries"
)
public class CountryController {
    private final ApplicationCountryService countryService;

    public CountryController(ApplicationCountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(
            summary = "Find all countries",
            description = "Returns a list of all countries."
    )
    @GetMapping
    public List<DisplayCountryDto> findAll(){
        return countryService.findAll();
    }

    @Operation(
            summary = "Find country by ID",
            description = "Returns the country with the specified ID."
    )
    @GetMapping("{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new country",
            description = "Creates a new country using the provided data."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto country){
        return countryService.save(country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a country",
            description = "Updates an existing country with the given ID."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto country) {
        return countryService.update(id, country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a country",
            description = "Deletes the country with the given ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
