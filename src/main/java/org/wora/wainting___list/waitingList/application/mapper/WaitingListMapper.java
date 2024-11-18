package org.wora.wainting___list.waitingList.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;
import org.wora.wainting___list.waitingList.domain.enums.Algorithm;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WaitingListMapper {
    WaitingListMapper INSTANCE= Mappers.getMapper(WaitingListMapper.class);

    @Mapping(target = "algorithm", source = "algorithm", qualifiedByName = "stringToAlgorithm")

    WaitingList toEntity(WaitingListRequestDTO dto);
    @Mapping(target = "algorithm", source = "algorithm", qualifiedByName = "algorithmToString")
    WaitingListResponseDTO toResponseDTO(WaitingList entity);

    List<WaitingListResponseDTO> toResponseDTOList(List<WaitingList> entities);
    @Named("stringToAlgorithm")
    default Algorithm stringToAlgorithm(String algorithm) {
        return Algorithm.valueOf(algorithm.toUpperCase());
    }

    @Named("algorithmToString")
    default String algorithmToString(Algorithm algorithm) {
        return algorithm.name();
    }
}


