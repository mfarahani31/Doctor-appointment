package com.blubank.doctorappointment.controller;


import com.blubank.doctorappointment.dto.OpenTimeDTO;
import com.blubank.doctorappointment.dto.OpenTimeResponseDTO;
import com.blubank.doctorappointment.service.OpenTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("/api/v1/openTimes")
public class OpenTimeController {
    private static final Logger logger =
            LoggerFactory.getLogger(OpenTimeController.class);
    private static final String NEW_OPENTIME_LOG = "OpenTime was added :{}";

    private final OpenTimeService opentimeService;

    @Autowired
    public OpenTimeController(OpenTimeService opentimeService) {
        this.opentimeService = opentimeService;
    }

    @Operation(summary = "Add open time of a doctor by doctorId")
    @ApiResponse(responseCode = "201", description = "Open Time is added", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Date.class))})
    @PostMapping("/addByDoctorId/{doctorId}")
    public ResponseEntity<OpenTimeResponseDTO> addOpenTime(@PathVariable Long doctorId, @Valid @RequestBody OpenTimeDTO openTimeDTO) {
        var openTimeResponseDTO = this.opentimeService.addOpenTime(doctorId, openTimeDTO);
        logger.info(NEW_OPENTIME_LOG, openTimeResponseDTO.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(openTimeResponseDTO);
    }
}
