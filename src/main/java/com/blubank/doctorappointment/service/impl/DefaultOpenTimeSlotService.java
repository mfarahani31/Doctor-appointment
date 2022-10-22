package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.OpenTimeSlotResponseDTO;
import com.blubank.doctorappointment.mapper.OpenTimeSlotMapper;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import com.blubank.doctorappointment.repository.OpenTimePeriodsRepository;
import com.blubank.doctorappointment.service.OpenTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultOpenTimeSlotService implements OpenTimeSlotService {
    private final OpenTimePeriodsRepository openTimePeriodsRepository;
    private final DefaultDoctorService doctorService;
    @Autowired
    public DefaultOpenTimeSlotService(OpenTimePeriodsRepository openTimePeriodsRepository, DefaultDoctorService doctorService) {
        this.openTimePeriodsRepository = openTimePeriodsRepository;
        this.doctorService = doctorService;
    }

    @Override
    public OpenTimeSlot save(OpenTimeSlot openTimeSlot) {
        return this.openTimePeriodsRepository.save(openTimeSlot);
    }

    @Override
    public List<OpenTimeSlotResponseDTO> getOpenTimeSlotsByDoctorId(Long doctorId) {
        var doctor = this.doctorService.findById(doctorId);
        var openTimeList = doctor.getOpenTimeList();
        var openTimeSlots = new ArrayList<OpenTimeSlot>();
        for (OpenTime openTime : openTimeList) {
            openTimeSlots.addAll(openTime.getOpenTimeSlotList());
        }
        return OpenTimeSlotMapper.INSTANCE.toOpenTimeSlotDTOList(openTimeSlots);
    }
}
