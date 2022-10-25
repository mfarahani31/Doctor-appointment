package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.constants.ErrorMessage;
import com.blubank.doctorappointment.dto.OpenTimeSlotResponseDTO;
import com.blubank.doctorappointment.exception.ElementNotFoundException;
import com.blubank.doctorappointment.mapper.OpenTimeSlotMapper;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import com.blubank.doctorappointment.repository.OpenTimeSlotsRepository;
import com.blubank.doctorappointment.service.OpenTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DefaultOpenTimeSlotService implements OpenTimeSlotService {
    private final OpenTimeSlotsRepository openTimeSlotsRepository;

    @Autowired
    public DefaultOpenTimeSlotService(OpenTimeSlotsRepository openTimeSlotsRepository) {
        this.openTimeSlotsRepository = openTimeSlotsRepository;
    }

    @Override
    public OpenTimeSlot save(OpenTimeSlot openTimeSlot) {
        return this.openTimeSlotsRepository.save(openTimeSlot);
    }

    @Override
    public List<OpenTimeSlotResponseDTO> getOpenTimeSlotsByDoctorId(Long doctorId) {
        var openTimeSlots = this.getTimeSlotsByDoctorId(doctorId);
        return OpenTimeSlotMapper.INSTANCE.toOpenTimeSlotDTOList(openTimeSlots);
    }

    private List<OpenTimeSlot> getTimeSlotsByDoctorId(Long doctorId) {
        return this.openTimeSlotsRepository.getAllByDoctorId(doctorId);
    }

    @Transactional
    @Override
    public Long deleteById(Long openTimeSlotId) {
        var openSlot = this.openTimeSlotsRepository.findById(openTimeSlotId).orElseThrow(
                () -> new ElementNotFoundException(ErrorMessage.ERROR_NOT_SLOT_EXIST + openTimeSlotId));
        if (openSlot.getPatientAppointment() != null)
            throw new HttpServerErrorException(HttpStatus.NOT_ACCEPTABLE, ErrorMessage.ERROR_SLOT_IS_BOOKED);
        this.openTimeSlotsRepository.deleteById(openTimeSlotId);
        return openTimeSlotId;
    }

    @Override
    public List<OpenTimeSlotResponseDTO> getOpenTimeSlotsForADayByDoctorId(Long doctorId, Date date) {
        var c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        var before = c.getTime();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        var after = c.getTime();
        var openTimeSlots = this.openTimeSlotsRepository.getAllByDoctorIdAndDate(doctorId, before, after);
        return OpenTimeSlotMapper.INSTANCE.toOpenTimeSlotDTOList(openTimeSlots);
    }
}
