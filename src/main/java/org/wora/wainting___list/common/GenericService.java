package org.wora.wainting___list.common;

import java.util.List;

public interface GenericService<RequestDTO, ResponseDTO, ID> {

    ResponseDTO create(RequestDTO requestDto);

    ResponseDTO getById(ID id);

    List<ResponseDTO> getAll();

    ResponseDTO update(ID id, RequestDTO requestDto);

    boolean delete(ID id);

    ResponseDTO findById(ID id);
}
