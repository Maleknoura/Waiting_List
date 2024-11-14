package org.wora.wainting___list.common;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface GenericMapper<Entity, RequestDTO, ResponseDTO> {

    Entity toEntity(RequestDTO dto);

    ResponseDTO toResponseDTO(Entity entity);

    List<ResponseDTO> toResponseDTOList(List<Entity> entities);
}

