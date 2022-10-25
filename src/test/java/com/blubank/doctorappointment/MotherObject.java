package com.blubank.doctorappointment;

import com.blubank.doctorappointment.dto.OpenTimeDTO;
import com.blubank.doctorappointment.model.Doctor;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.OpenTimeSlot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MotherObject {
    public static String getAnyOpenTimeInJson() {

        return "{\n" +
                "  \"date\": \"2022-10-20T10:10:45.083Z\",\n" +
                "  \"startTime\": \"2022-10-19T10:01:45.083Z\",\n" +
                "  \"endTime\": \"2022-10-19T11:55:45.083Z\"\n" +
                "}";
    }

    public static String getAnyDate() {
        return "2022-10-20T07:08:33.839Z";
    }

    public static String getAnyOpenTimeEndEarlierThanStartInJson() {

        return "{\n" +
                "  \"date\": \"2022-10-20T10:10:45.083Z\",\n" +
                "  \"startTime\": \"2022-10-19T10:45:45.083Z\",\n" +
                "  \"endTime\": \"2022-10-19T10:40:45.083Z\"\n" +
                "}";
    }

    public static String getAnyOpenTimePeriodLessThan30MinInJson() {

        return "{\n" +
                "  \"date\": \"2022-10-20T10:10:45.083Z\",\n" +
                "  \"startTime\": \"2022-10-19T10:45:45.083Z\",\n" +
                "  \"endTime\": \"2022-10-19T10:58:45.083Z\"\n" +
                "}";
    }

    public static Doctor getAnyDoctor() {
        return new Doctor("John", null);
    }

    public static Doctor getAnyDoctorWithOpenTime() {
        return new Doctor("John", List.of(getAnyOpenTimeObjectWithoutDoctor()));
    }

    public static OpenTime getAnyOpenTimeObjectWithoutDoctor() {
        var dt = new Date();
        var c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 2);
        c.add(Calendar.HOUR, 1);
        dt = c.getTime();
        var openTime = new OpenTime(dt, dt, dt, getAnyDoctor(), openTimeSlotList());
        c.add(Calendar.HOUR, 2);
        dt = c.getTime();
        openTime.setEndTime(dt);
        return openTime;
    }

    public static OpenTime getAnyOpenTimeObject() {
        var dt = new Date();
        var c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 2);
        c.add(Calendar.HOUR, 1);
        dt = c.getTime();
        var openTime = new OpenTime(dt, dt, dt, getAnyDoctor(), openTimeSlotList());
        c.add(Calendar.HOUR, 2);
        dt = c.getTime();
        openTime.setEndTime(dt);
        return openTime;
    }

    public static OpenTimeDTO getAnyOpenTimeDTOObject() {
        var dt = new Date();
        var c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 2);
        c.add(Calendar.HOUR, 1);
        dt = c.getTime();
        var openTimeDTO = new OpenTimeDTO(dt, dt, dt);
        c.add(Calendar.HOUR, 2);
        dt = c.getTime();
        openTimeDTO.setEndTime(dt);
        return openTimeDTO;
    }

    public static OpenTime getAnyOpenTimeObjectEmptySlot() {
        return new OpenTime(new Date(), new Date(), new Date(), getAnyDoctor(), null);
    }

    private static List<OpenTimeSlot> openTimeSlotList() {
        var openTimeSlot1 = new OpenTimeSlot(new Date(), getAnyOpenTimeObjectEmptySlot(), null);
        var openTimeSlot2 = new OpenTimeSlot(new Date(), getAnyOpenTimeObjectEmptySlot(), null);

        var openTimeSlotList = new ArrayList<OpenTimeSlot>();
        openTimeSlotList.add(openTimeSlot1);
        openTimeSlotList.add(openTimeSlot2);
        return openTimeSlotList;
    }

    public static OpenTimeSlot getAnyOpenTimeSlot() {
        return new OpenTimeSlot(new Date(), getAnyOpenTimeObject(), null);
    }

    public static Date getAnyBeforeDate() {
        var date = new Date();
        var c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    public static Date getAnyAfterDate() {
        var date = new Date();
        var c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, +1);
        return c.getTime();
    }

    public static List<OpenTimeSlot> getAnyOpenTimeSlotList() {
        return List.of(getAnyOpenTimeSlot());
    }
}
