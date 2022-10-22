package com.blubank.doctorappointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenTimeResponseDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<Date> openTimes;
}
