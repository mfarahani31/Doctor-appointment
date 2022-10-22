package com.blubank.doctorappointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.lang.Nullable;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "OPENTIMESLOTS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class OpenTimeSlot extends BaseEntity implements Serializable {

    @Column(name = "open_time_appointment")
    private Date openTimeAppointment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "open_time_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OpenTime openTime;

    @OneToOne(mappedBy = "openTimeSlot")
    @Nullable
    private PatientAppointment patientAppointment;

}
