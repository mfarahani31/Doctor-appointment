package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.model.Doctor;

import java.util.Optional;

public interface DoctorService {
    Doctor findById(Long doctorId);
}
