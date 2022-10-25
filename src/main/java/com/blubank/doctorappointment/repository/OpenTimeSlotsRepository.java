package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.OpenTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OpenTimeSlotsRepository extends JpaRepository<OpenTimeSlot, Long> {


    @Query(value = "select o from OpenTimeSlot o where o.openTime.doctor.id = :doctorId")
    List<OpenTimeSlot> getAllByDoctorId(@Param("doctorId") Long doctorId);

    @Query("select o from OpenTimeSlot o where o.openTime.doctor.id = ?1 and o.openTime.date between ?2 and ?3")
    List<OpenTimeSlot> getAllByDoctorIdAndDate(Long id, Date dateStart, Date dateEnd);


}
