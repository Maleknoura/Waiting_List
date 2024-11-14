package org.wora.wainting___list.waitingList.application.impl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wora.wainting___list.waitingList.application.mapper.WaitingListMapper;
import org.wora.wainting___list.waitingList.application.service.WaintingListService;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;
import org.wora.wainting___list.waitingList.domain.repository.WaitingListRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WaitingListServiceImpl implements WaintingListService {

    private final WaitingListRepository waitingListRepository;
    private final WaitingListMapper waitingListMapper;

    @Override
    public WaitingListResponseDTO create(WaitingListRequestDTO requestDto) {
        WaitingList waitingList = waitingListMapper.toEntity(requestDto);
        WaitingList savedWaitingList = waitingListRepository.save(waitingList);
        return waitingListMapper.toResponseDTO(savedWaitingList);
    }

    @Override
    public WaitingListResponseDTO getById(Long id) {
        WaitingList waitingList = waitingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WaitingList not found with id " + id));
        return waitingListMapper.toResponseDTO(waitingList);
    }

    @Override
    public List<WaitingListResponseDTO> getAll() {
        return waitingListRepository.findAll().stream()
                .map(waitingListMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WaitingListResponseDTO update(Long id, WaitingListRequestDTO requestDto) {
        WaitingList waitingList = waitingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WaitingList not found with id " + id));

        waitingList.setDate(requestDto.date());
        waitingList.setAlgorithm(requestDto.algorithm());
        waitingList.setCapacity(requestDto.capacity());
        waitingList.setMode(requestDto.mode());

        WaitingList updatedWaitingList = waitingListRepository.save(waitingList);
        return waitingListMapper.toResponseDTO(updatedWaitingList);
    }

    @Override
    public boolean delete(Long id) {
        if (waitingListRepository.existsById(id)) {
            waitingListRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


