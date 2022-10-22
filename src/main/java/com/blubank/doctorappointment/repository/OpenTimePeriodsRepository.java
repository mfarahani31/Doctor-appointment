package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.OpenTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenTimePeriodsRepository extends JpaRepository<OpenTimeSlot,Long> {
}
