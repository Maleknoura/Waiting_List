package org.wora.wainting___list.waitingList.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.wora.wainting___list.common.GenericMapper;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;

@Mapper(componentModel = "spring")
public interface WaitingListMapper extends GenericMapper<WaitingList, WaitingListRequestDTO, WaitingListResponseDTO> {

    WaitingListMapper INSTANCE = Mappers.getMapper(WaitingListMapper.class);
}

