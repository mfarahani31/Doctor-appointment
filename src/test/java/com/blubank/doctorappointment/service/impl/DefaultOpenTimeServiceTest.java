package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.MotherObject;
import com.blubank.doctorappointment.dto.OpenTimeDTO;
import com.blubank.doctorappointment.exception.ElementNotFoundException;
import com.blubank.doctorappointment.model.Doctor;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import com.blubank.doctorappointment.repository.OpenTimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultOpenTimeServiceTest {

    @InjectMocks
    DefaultOpenTimeService openTimeService;

    @Mock
    OpenTimeRepository openTimeRepository;

    @Mock
    DefaultDoctorService doctorService;

    @Mock
    DefaultOpenTimeSlotService openTimeSlotService;

    Doctor doctor;
    OpenTime openTime;
    OpenTimeDTO openTimeDTO;
    OpenTimeSlot openTimeSlot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctor = MotherObject.getAnyDoctor();
        openTime = MotherObject.getAnyOpenTimeObject();
        openTimeDTO = MotherObject.getAnyOpenTimeDTOObject();
        openTimeSlot = MotherObject.getAnyOpenTimeSlot();
    }

    @Test
    void testContext() {
        assertNotNull(openTimeService);
        assertNotNull(doctorService);
        assertNotNull(openTimeSlotService);
        assertNotNull(openTimeRepository);
    }

    @Test
    void addOpenTime() {
        when(doctorService.findById(1L)).thenReturn(this.doctor);
        when(openTimeRepository.save(this.openTime)).thenReturn(this.openTime);
        when(openTimeSlotService.save(this.openTimeSlot)).thenReturn(this.openTimeSlot);

        var openTimeResponseDTO = openTimeService.addOpenTime(1L, this.openTimeDTO);

        assertNotNull(openTimeResponseDTO.getOpenTimes());
    }

    @Test
    void addOpenTime_whenDoctorIdNotExist_thenThrowsException() {
        when(doctorService.findById(1L)).thenThrow(ElementNotFoundException.class);
        assertThrows(ElementNotFoundException.class, () -> openTimeService.addOpenTime(1L, this.openTimeDTO));
    }

    @Test
    void save() {
        when(openTimeRepository.save(this.openTime)).thenReturn(this.openTime);

        var openTime = openTimeService.save(this.openTime);

        assertNotNull(openTime);
        assertEquals(this.doctor,openTime.getDoctor());
    }
}