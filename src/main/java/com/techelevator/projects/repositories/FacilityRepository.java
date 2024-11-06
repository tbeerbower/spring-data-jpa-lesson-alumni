package com.techelevator.projects.repositories;

import com.techelevator.projects.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
