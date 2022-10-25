package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.model.Doctor;

public interface DoctorService {
    Doctor findById(Long doctorId);
}
