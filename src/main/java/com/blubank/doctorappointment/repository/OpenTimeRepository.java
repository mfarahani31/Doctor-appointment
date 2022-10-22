package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.OpenTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenTimeRepository extends JpaRepository<OpenTime,Long> {
}
