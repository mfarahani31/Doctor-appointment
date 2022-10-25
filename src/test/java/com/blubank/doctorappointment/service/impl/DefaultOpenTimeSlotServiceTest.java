package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.MotherObject;
import com.blubank.doctorappointment.dto.OpenTimeSlotResponseDTO;
import com.blubank.doctorappointment.exception.ElementNotFoundException;
import com.blubank.doctorappointment.model.Doctor;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import com.blubank.doctorappointment.repository.OpenTimeSlotsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultOpenTimeSlotServiceTest {

    @InjectMocks
    DefaultOpenTimeSlotService openTimeSlotService;

    @Mock
    OpenTimeSlotsRepository openTimeSlotsRepository;

    @Mock
    DefaultDoctorService doctorService;

    OpenTimeSlot openTimeSlot;
    Doctor doctor;
    OpenTime openTime;
    Date before;
    Date after;
    List<OpenTimeSlot> openTimeSlotList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        openTimeSlot = MotherObject.getAnyOpenTimeSlot();
        doctor = MotherObject.getAnyDoctor();
        openTime = MotherObject.getAnyOpenTimeObject();
        before = MotherObject.getAnyBeforeDate();
        after = MotherObject.getAnyAfterDate();
        openTimeSlotList = MotherObject.getAnyOpenTimeSlotList();
    }

    @Test
    void testContext() {
        assertNotNull(openTimeSlotService);
        assertNotNull(openTimeSlotsRepository);
        assertNotNull(doctorService);
    }

    @Test
    void save() {
        when(openTimeSlotsRepository.save(this.openTimeSlot)).thenReturn(this.openTimeSlot);

        var openTimeSlot = this.openTimeSlotService.save(this.openTimeSlot);

        assertNotNull(openTimeSlot);
        assertEquals(openTimeSlot, this.openTimeSlot);
    }

    @Test
    void getOpenTimeSlotsByDoctorId() {
        when(doctorService.findById(1L)).thenReturn(MotherObject.getAnyDoctorWithOpenTime());

        var openTimeSlotResponseDTOs = this.openTimeSlotService.getOpenTimeSlotsByDoctorId(1L);

        assertNotNull(openTimeSlotResponseDTOs);
    }

    @Test
    void deleteBySlotId() {
        when(openTimeSlotsRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(openTimeSlot));
        doNothing().when(openTimeSlotsRepository).deleteById(1L);


        this.openTimeSlotService.deleteById(1L);


        verify(openTimeSlotsRepository, times(1)).findById(1L);
        verify(openTimeSlotsRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteById_whenNotExist_returnError() {
        when(doctorService.findById(5L)).thenThrow(ElementNotFoundException.class);
        assertThrows(ElementNotFoundException.class,
                () -> openTimeSlotService.deleteById(1L));
    }

    @Test
    void getOpenTimeSlotsForADayByDoctorId() {
        when(openTimeSlotsRepository.getAllByDoctorIdAndDate(1L, before, after))
                .thenReturn(this.openTimeSlotList);

        List<OpenTimeSlotResponseDTO> openTimeSlotList = this.openTimeSlotService.getOpenTimeSlotsForADayByDoctorId(1L, new Date());


        assertNotNull(openTimeSlotList);
    }
}