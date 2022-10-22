package com.blubank.doctorappointment.dto;

import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.PatientAppointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenTimeSlotResponseDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openTimeAppointment;

    @Nullable
    private PatientAppointment patientAppointment;
}
