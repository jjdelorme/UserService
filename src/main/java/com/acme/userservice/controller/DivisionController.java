package com.acme.userservice.controller;

import com.acme.userservice.dto.DivisionDTO;
import com.acme.userservice.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/divisions")
public class DivisionController {

    private final DivisionService divisionService;

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping
    public ResponseEntity<DivisionDTO> createDivision(@RequestBody DivisionDTO divisionDTO) {
        DivisionDTO createdDivision = divisionService.createDivision(divisionDTO);
        return new ResponseEntity<>(createdDivision, HttpStatus.CREATED);
    }

    @GetMapping("/{divisionId}")
    public ResponseEntity<DivisionDTO> getDivision(@PathVariable Long divisionId) {
        Optional<DivisionDTO> division = divisionService.getDivision(divisionId);
        return division.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DivisionDTO>> getAllDivisions() {
        List<DivisionDTO> divisions = divisionService.getAllDivisions();
        return ResponseEntity.ok(divisions);
    }

    @PutMapping
    public ResponseEntity<DivisionDTO> updateDivision(@RequestBody DivisionDTO divisionDTO) {
        Optional<DivisionDTO> updatedDivision = divisionService.updateDivision(divisionDTO);
        return updatedDivision.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{divisionId}")
    public ResponseEntity<Void> deleteDivision(@PathVariable Long divisionId) {
        boolean deleted = divisionService.deleteDivision(divisionId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
