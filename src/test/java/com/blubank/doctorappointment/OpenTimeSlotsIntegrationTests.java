package com.blubank.doctorappointment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OpenTimeSlotsIntegrationTests {

    @Autowired
    private MockMvc mockMvc;


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

    @Test
    public void addOpenTimeEndEarlierThanStartByDoctorId() throws Exception {
        mockMvc.perform(get("/api/v1/openTimeSlots/getByDoctorId/5"))
                .andDo(print())
                .andExpect(
                        status().isNotFound());
    }

    @Test
    public void getOpenTimeEndEarlierThanStartByDoctorId() throws Exception {
        mockMvc.perform(get("/api/v1/openTimeSlots/getByDoctorId/1"))
                .andDo(print())
                .andExpect(jsonPath("$").isEmpty())
                .andExpect(
                        status().isOk());
    }
//    @Test
//    public void addOpenTimePeriodLessThan30MinByDoctorId() throws Exception {
//        mockMvc.perform(post("/api/v1/openTimes/addByDoctorId/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(MotherObject.getAnyOpenTimePeriodLessThan30MinInJson())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.openTimes.length()").value(0))
//                .andExpect(
//                        status().isCreated());
//    }
//
//    private String getRequestBodyAsString(Object object) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        return ow.writeValueAsString(object);
//    }


}
