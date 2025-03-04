/*
 * Copyright © 2025 ACME Corp.
 *
 * This work is protected by copyright law and international treaties.
 * Unauthorized reproduction or distribution of this work, or any portion
 * thereof, may result in severe civil and criminal penalties, and will be
 * prosecuted to the maximum extent possible under the law.
 *
 * ACME Corp. Confidential. All Rights Reserved.
 *
 * This software contains the proprietary and confidential information of
 * ACME Corp., and may not be copied, reproduced, or distributed in any
 * form without the express written permission of ACME Corp.
 */

package com.acme.userservice.service;

import com.acme.userservice.annotation.AuditLog;
import com.acme.userservice.dto.DivisionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DivisionService {

    // In a real application, you would use a database here
    private final List<DivisionDTO> divisions = new ArrayList<>();
    private final AtomicLong nextDivisionId = new AtomicLong(1);

    public DivisionService() {
        // Initialize with some sample data
        createDivision(new DivisionDTO().setDivisionName("Sales").setDescription("Sales Division"));
        createDivision(new DivisionDTO().setDivisionName("Marketing").setDescription("Marketing Division"));
        createDivision(new DivisionDTO().setDivisionName("Engineering").setDescription("Engineering Division"));
    }

    @AuditLog(description = "Creates a new division")
    public DivisionDTO createDivision(DivisionDTO divisionDTO) {
        long divisionId = nextDivisionId.getAndIncrement();
        divisionDTO.setDivisionId(divisionId);
        divisions.add(divisionDTO);
        System.out.println("Created division: " + divisionDTO.getDivisionId() + " - " + divisionDTO.getDivisionName());
        return divisionDTO;
    }

    public Optional<DivisionDTO> getDivision(Long divisionId) {
        System.out.println("Getting division: " + divisionId);
        return divisions.stream()
                .filter(division -> division.getDivisionId().equals(divisionId))
                .findFirst();
    }

    public List<DivisionDTO> getAllDivisions() {
        System.out.println("Getting all divisions");
        return new ArrayList<>(divisions); // Return a copy to avoid external modification
    }

    @AuditLog(description = "Updates division information")
    public Optional<DivisionDTO> updateDivision(DivisionDTO divisionDTO) {
        System.out.println("Updating division: " + divisionDTO.getDivisionId());
        Optional<DivisionDTO> existingDivision = getDivision(divisionDTO.getDivisionId());
        if (existingDivision.isPresent()) {
            DivisionDTO divisionToUpdate = existingDivision.get();
            divisionToUpdate.setDivisionName(divisionDTO.getDivisionName());
            divisionToUpdate.setDescription(divisionDTO.getDescription());
            return Optional.of(divisionToUpdate);
        } else {
            return Optional.empty();
        }
    }

    @AuditLog(description = "Deletes a division")
    public boolean deleteDivision(Long divisionId) {
        System.out.println("Deleting division: " + divisionId);
        return divisions.removeIf(division -> division.getDivisionId().equals(divisionId));
    }
}
