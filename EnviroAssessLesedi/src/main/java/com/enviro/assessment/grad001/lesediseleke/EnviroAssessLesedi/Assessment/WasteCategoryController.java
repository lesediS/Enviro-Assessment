package com.enviro.assessment.grad001.lesediseleke.EnviroAssessLesedi.Assessment;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Create a REST URL mapping
@RestController
@RequestMapping("/api/categories")
public class WasteCategoryController {
    private final WasteCategoryRepo wasteRepo;

    public WasteCategoryController(WasteCategoryRepo wasteRepo) { //Dependency injection using the constructor
        this.wasteRepo = wasteRepo;
    }

    @GetMapping //Get a List of all the categories, no need for a URL for this retrieval
    public ResponseEntity<List<WasteCategory>> getAllCategories() {
        return ResponseEntity.ok(wasteRepo.findAll());
    }

    @GetMapping("/{id}") //Get a List of the category at a specific id, so a URL is needed and a LIST is not
    public ResponseEntity<WasteCategory> getCatById(@PathVariable Integer id) {
        return wasteRepo.findById(id) //Find by id...
                .map(ResponseEntity::ok) //... and if found return with OK status 200...
                .orElse(ResponseEntity.notFound().build()); //... or else respond with error 404 not found
    }

    @PostMapping //Posting/Creating a new waste category, no need for a specific URL
    public ResponseEntity<WasteCategory> createCategory(@Valid @RequestBody WasteCategory category) {
        return ResponseEntity.ok(wasteRepo.save(category));
    }

    @PutMapping //Putting (updating) specific category by ID
    public ResponseEntity<WasteCategory> updateCatById(@PathVariable int id, @Valid @RequestBody WasteCategory updatedCat) {
        return wasteRepo.findById(id).map(existingCat -> { //Updating all necessary fields and saving at the end...
            existingCat.setName(updatedCat.getName());
            existingCat.setDescription(updatedCat.getDescription());
            existingCat.setDisposalGuideline(updatedCat.getDisposalGuideline());
            existingCat.setTips(updatedCat.getTips());
            return ResponseEntity.ok(wasteRepo.save(existingCat));
        }).orElse(ResponseEntity.notFound().build()); //... or else return status 404 not found error
    }

    @DeleteMapping("/{id}") //Delete a category based on the id
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        if (wasteRepo.existsById(id)){
            wasteRepo.deleteById(id);
            return ResponseEntity.noContent().build(); //If found, delete and return no content
        } else {
            return ResponseEntity.notFound().build(); //If NOT found, return not found error
        }
    }
}