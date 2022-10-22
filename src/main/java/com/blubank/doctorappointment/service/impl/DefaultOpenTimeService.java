package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.OpenTimeResponseDTO;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import com.blubank.doctorappointment.repository.OpenTimeRepository;
import com.blubank.doctorappointment.service.OpenTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class DefaultOpenTimeService implements OpenTimeService {
    private final DefaultOpenTimeSlotService openTimeSlotService;
    private final OpenTimeRepository openTimeRepository;
    private final DefaultDoctorService doctorService;

    @Autowired
    public DefaultOpenTimeService(DefaultOpenTimeSlotService openTimeSlotService, OpenTimeRepository openTimeRepository, DefaultDoctorService doctorService) {
        this.openTimeSlotService = openTimeSlotService;
        this.openTimeRepository = openTimeRepository;
        this.doctorService = doctorService;
    }

    @Transactional
    @Override
    public OpenTimeResponseDTO addOpenTime(Long doctorId, OpenTime openTime) {
        var doctor = this.doctorService.findById(doctorId);
        openTime.setDoctor(doctor);
        this.save(openTime);
        if (TimeUnit.MILLISECONDS.toMinutes(openTime.getEndTime().getTime() - openTime.getStartTime().getTime()) < 30)
            return new OpenTimeResponseDTO(new ArrayList<>());
        var openTimeResponseDTO = this.getIntervalBetweenStartAndEnd(openTime.getDate(),
                openTime.getStartTime(),
                openTime.getEndTime(),
                30);
        for (var period : openTimeResponseDTO.getOpenTimes()) {
            this.openTimeSlotService.save(new OpenTimeSlot(period, openTime, null));
        }
        return openTimeResponseDTO;
    }

    @Override
    public OpenTime save(OpenTime openTime) {
        return this.openTimeRepository.save(openTime);
    }


    private OpenTimeResponseDTO getIntervalBetweenStartAndEnd(Date date, Date start, Date end, int interval) {
        var c = getCalendar(date);
        var dates = new ArrayList<Date>();
        var startInMin = (start.getHours() * 60) + start.getMinutes();
        var endInMin = (end.getHours() * 60) + end.getMinutes();
        for (int time = startInMin; time < endInMin; time += interval) {
            System.out.println(String.format("%02d:%02d", time / 60, time % 60));
            c.set(Calendar.HOUR_OF_DAY, time / 60);
            c.set(Calendar.MINUTE, time % 60);
            dates.add(c.getTime());
        }
        return new OpenTimeResponseDTO(dates);
    }

    private Calendar getCalendar(Date date) {
        var c = Calendar.getInstance();
        c.set(Calendar.YEAR, date.getYear() + 1900);
        c.set(Calendar.MONTH, date.getMonth());
        c.set(Calendar.DAY_OF_MONTH, date.getDate());
        return c;
    }
}
