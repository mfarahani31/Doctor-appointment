package com.blubank.doctorappointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PATIENTAPPOINTMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class PatientAppointment extends BaseEntity implements Serializable {

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "open_time_slot_id", referencedColumnName = "id")
    @JsonIgnore
    private OpenTimeSlot openTimeSlot;
}
