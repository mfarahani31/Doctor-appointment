package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.OpenTimeSlotResponseDTO;
import com.blubank.doctorappointment.model.OpenTimeSlot;

import java.util.List;

public interface OpenTimeSlotService {
    OpenTimeSlot save(OpenTimeSlot openTimeSlot);

    List<OpenTimeSlotResponseDTO> getOpenTimeSlotsByDoctorId(Long doctorId);
}
