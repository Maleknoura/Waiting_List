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
        List<WaitingList> waitingLists = waitingListRepository.findAll();
        return waitingListMapper.toResponseDTOList(waitingLists);
    }

    @Override
    public WaitingListResponseDTO update(Long id, WaitingListRequestDTO requestDto) {
        if (!waitingListRepository.existsById(id)) {
            throw new RuntimeException("WaitingList not found with id " + id);
        }
        WaitingList waitingList = waitingListMapper.toEntity(requestDto);
        waitingList.setId(id);
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

    @Override
    public WaitingListResponseDTO findById(Long id) {
        WaitingList waitingList = waitingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Waiting List not found"));
        return waitingListMapper.toResponseDTO(waitingList);
    }


}
