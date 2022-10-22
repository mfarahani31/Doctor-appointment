package com.blubank.doctorappointment.controller;


import com.blubank.doctorappointment.dto.OpenTimeSlotResponseDTO;
import com.blubank.doctorappointment.service.OpenTimeService;
import com.blubank.doctorappointment.service.OpenTimeSlotService;
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

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("/api/v1/openTimeSlots")
public class OpenTimeSlotController {
    private static final Logger logger =
            LoggerFactory.getLogger(OpenTimeSlotController.class);
    private static final String OPENTIME_SLOTS_LOG = "OpenTime slots are :{}";

    private final OpenTimeSlotService openTimeSlotService;

    @Autowired
    public OpenTimeSlotController(OpenTimeService opentimeService, OpenTimeSlotService openTimeSlotService) {
        this.openTimeSlotService = openTimeSlotService;
    }

    @Operation(summary = "View open time slots of a doctor by doctorId")
    @ApiResponse(responseCode = "200", description = "Found the Open Time Slots", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OpenTimeSlotResponseDTO.class))})
    @GetMapping("/getByDoctorId/{doctorId}")
    public ResponseEntity<List<OpenTimeSlotResponseDTO>> getOpenTimeSlotsByDoctorId(@PathVariable Long doctorId) {
        var openTimeResponseDTOs = this.openTimeSlotService.getOpenTimeSlotsByDoctorId(doctorId);
        logger.info(OPENTIME_SLOTS_LOG, openTimeResponseDTOs.toString());
        return ResponseEntity.status(HttpStatus.OK).body(openTimeResponseDTOs);
    }
}
