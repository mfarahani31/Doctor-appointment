package com.blubank.doctorappointment.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DOCTORS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Doctor extends BaseEntity implements Serializable {
    @Column
    private String name;

    @OneToMany(mappedBy = "doctor")
    private List<OpenTime> openTimeList;
}
