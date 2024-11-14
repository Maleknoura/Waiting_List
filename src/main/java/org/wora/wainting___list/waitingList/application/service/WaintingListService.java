package org.wora.wainting___list.waitingList.application.service;

import org.wora.wainting___list.common.GenericService;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.yaml.snakeyaml.events.Event;

public interface WaintingListService extends GenericService<WaitingListRequestDTO, WaitingListResponseDTO,Long> {
}
