package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.OpenTimeSlotResponseDTO;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenTimeSlotMapper {
    OpenTimeSlotMapper INSTANCE = Mappers.getMapper(OpenTimeSlotMapper.class);

    List<OpenTimeSlotResponseDTO> toOpenTimeSlotDTOList(List<OpenTimeSlot> openTimeSlotList);

}
