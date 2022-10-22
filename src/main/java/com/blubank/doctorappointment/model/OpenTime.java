package com.blubank.doctorappointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OPENTIMES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class OpenTime extends BaseEntity implements Serializable {

    @Column(name = "open_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "end_Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Doctor doctor;


    @OneToMany(mappedBy = "openTime")
    private List<OpenTimeSlot> openTimeSlotList;

}
