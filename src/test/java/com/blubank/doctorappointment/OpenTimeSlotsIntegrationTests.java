package com.blubank.doctorappointment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OpenTimeSlotsIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void getOpenTimeSlots_ifNoAppointmentSet_thenReturnEmptyList() throws Exception {
//        mockMvc.perform(get("/api/v1/openTimeSlots/getByDoctorId/1"))
//                .andDo(print())
//                .andExpect(jsonPath("$.[0].patientAppointment").isEmpty())
//                .andExpect(
//                        status().isOk());
//    }

    @Test
    public void getOpenTimeSlotsByDoctorId() throws Exception {
        mockMvc.perform(post("/api/v1/openTimes/addByDoctorId/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MotherObject.getAnyOpenTimeInJson())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        mockMvc.perform(get("/api/v1/openTimeSlots/getByDoctorId/1"))
                .andDo(print())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(
                        status().isOk());
    }

//    @Test
//    public void getOpenTimeSlots_whenDoctorId_notExist_thenReturnNotFound() throws Exception {
//        mockMvc.perform(get("/api/v1/openTimeSlots/getByDoctorId/50"))
//                .andDo(print())
//                .andExpect(
//                        status().isNotFound());
//    }

    @Test
    public void deleteTimeSlotById() throws Exception {
        mockMvc.perform(post("/api/v1/openTimes/addByDoctorId/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MotherObject.getAnyOpenTimeInJson())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());


        mockMvc.perform(delete("/api/v1/openTimeSlots/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(
                        status().isNoContent());
    }

    @Test
    public void getOpenTimeSlotsForADayByDoctorId() throws Exception {
        mockMvc.perform(post("/api/v1/openTimes/addByDoctorId/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MotherObject.getAnyOpenTimeInJson())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        mockMvc.perform(get("/api/v1/openTimeSlots/getByDoctorIdAndDate/1")
                        .param("date", MotherObject.getAnyDate()))
                .andDo(print())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(
                        status().isOk());
    }

}
