package org.wora.wainting___list.waitingList.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WaitingListMapper {

    WaitingList toEntity(WaitingListRequestDTO dto);

    WaitingListResponseDTO toResponseDTO(WaitingList entity);

    List<WaitingListResponseDTO> toResponseDTOList(List<WaitingList> entities);
}


