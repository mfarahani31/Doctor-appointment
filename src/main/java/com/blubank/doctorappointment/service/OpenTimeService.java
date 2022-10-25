package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.OpenTimeDTO;
import com.blubank.doctorappointment.dto.OpenTimeResponseDTO;
import com.blubank.doctorappointment.model.OpenTime;

public interface OpenTimeService {

    OpenTimeResponseDTO addOpenTime(Long doctorId, OpenTimeDTO openTimeDTO);

    OpenTime save(OpenTime openTime);
}
