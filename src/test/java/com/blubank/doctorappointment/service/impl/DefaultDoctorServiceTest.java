package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.MotherObject;
import com.blubank.doctorappointment.exception.ElementNotFoundException;
import com.blubank.doctorappointment.model.Doctor;
import com.blubank.doctorappointment.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultDoctorServiceTest {

    @InjectMocks
    DefaultDoctorService doctorService;

    @Mock
    DoctorRepository doctorRepository;

    Doctor doctor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctor = MotherObject.getAnyDoctor();
    }

    @Test
    void testContext() {
        assertNotNull(doctorService);
        assertNotNull(doctorRepository);
    }

    @Test
    void findById() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(this.doctor));

        var doctor = this.doctorService.findById(1L);

        assertEquals("John", doctor.getName());
    }

    @Test
    void findById_throws_exception() {
        when(doctorRepository.findById(1L)).thenThrow(ElementNotFoundException.class);
        assertThrows(ElementNotFoundException.class, () -> doctorService.findById(1L));
    }
}