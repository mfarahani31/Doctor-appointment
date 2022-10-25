package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.constants.ErrorMessage;
import com.blubank.doctorappointment.exception.ElementNotFoundException;
import com.blubank.doctorappointment.model.Doctor;
import com.blubank.doctorappointment.repository.DoctorRepository;
import com.blubank.doctorappointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultDoctorService implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DefaultDoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor findById(Long doctorId) {
        return this.doctorRepository.findById(doctorId).orElseThrow(
                () -> new ElementNotFoundException(ErrorMessage.ERROR_NOT_USER_EXIST + doctorId));
    }
}
