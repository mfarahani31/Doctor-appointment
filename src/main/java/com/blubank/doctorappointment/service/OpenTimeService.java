package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.OpenTimeResponseDTO;
import com.blubank.doctorappointment.model.OpenTime;

import java.util.Date;
import java.util.List;

public interface OpenTimeService {

    OpenTimeResponseDTO addOpenTime(Long doctorId, OpenTime openTime);

    OpenTime save(OpenTime openTime);
}
