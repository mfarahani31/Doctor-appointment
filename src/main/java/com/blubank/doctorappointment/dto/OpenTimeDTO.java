package com.blubank.doctorappointment.dto;

import com.blubank.doctorappointment.validation.CompareTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@CompareTime(before = "startTime", after = "endTime", message = "The start time must be lower than the end time")
public class OpenTimeDTO {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "IRST")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "IRST")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Date startTime;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "IRST")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Date endTime;

}
