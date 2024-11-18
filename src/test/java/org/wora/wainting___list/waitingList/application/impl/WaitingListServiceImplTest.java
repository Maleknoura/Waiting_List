package org.wora.wainting___list.waitingList.application.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wora.wainting___list.waitingList.application.mapper.WaitingListMapper;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;
import org.wora.wainting___list.waitingList.domain.enums.Algorithm;
import org.wora.wainting___list.waitingList.domain.repository.WaitingListRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WaitingListServiceImplTest {

    @Mock
    private WaitingListRepository waitingListRepository;

    @Mock
    private WaitingListMapper waitingListMapper;

    @InjectMocks
    private WaitingListServiceImpl waitingListService;

    private WaitingListRequestDTO requestDto;
    private WaitingList waitingList;
    private WaitingListResponseDTO responseDto;

    @BeforeEach
    void setUp() {

    }

    @Test
    void create_ShouldReturnResponseDTO() {
        Long id = 1L;
        LocalDate date = LocalDate.now();
        Algorithm algorithm = Algorithm.FIFO;
        Integer capacity = 20;
        String mode = "ONLINE";

        WaitingListRequestDTO requestDto = new WaitingListRequestDTO(date, algorithm, capacity, mode);

        WaitingList waitingList = new WaitingList();
        waitingList.setId(id);
        waitingList.setDate(date);
        waitingList.setAlgorithm(algorithm);
        waitingList.setCapacity(capacity);
        waitingList.setMode(mode);

        WaitingListResponseDTO responseDto = new WaitingListResponseDTO(id, date, algorithm, capacity, mode);

        when(waitingListMapper.toEntity(requestDto)).thenReturn(waitingList);
        when(waitingListRepository.save(any(WaitingList.class))).thenReturn(waitingList);
        when(waitingListMapper.toResponseDTO(waitingList)).thenReturn(responseDto);

        WaitingListResponseDTO result = waitingListService.create(requestDto);

        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(waitingListMapper).toEntity(requestDto);
        verify(waitingListRepository).save(waitingList);
        verify(waitingListMapper).toResponseDTO(waitingList);
    }


    @Test
    void getById_WhenExists_ShouldReturnResponseDTO() {
        Long id = 1L;
        LocalDate date = LocalDate.now();
        Algorithm algorithm = Algorithm.FIFO;
        Integer capacity = 20;
        String mode = "ONLINE";

        WaitingList waitingList = new WaitingList();
        waitingList.setId(id);
        waitingList.setDate(date);
        waitingList.setAlgorithm(algorithm);
        waitingList.setCapacity(capacity);
        waitingList.setMode(mode);

        WaitingListResponseDTO responseDto = new WaitingListResponseDTO(id, date, algorithm, capacity, mode);

        when(waitingListRepository.findById(id)).thenReturn(Optional.of(waitingList));

        when(waitingListMapper.toResponseDTO(waitingList)).thenReturn(responseDto);

        WaitingListResponseDTO result = waitingListService.getById(id);

        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(waitingListRepository).findById(id);
        verify(waitingListMapper).toResponseDTO(waitingList);
    }

    @Test
    void getById_WhenNotExists_ShouldThrowException() {

        Long id = 1L;
        when(waitingListRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> waitingListService.getById(id));
        verify(waitingListRepository).findById(id);
    }

    @Test
    void getAll_ShouldReturnListOfResponseDTOs() {

        List<WaitingList> waitingLists = Arrays.asList(waitingList);
        List<WaitingListResponseDTO> expectedResponses = Arrays.asList(responseDto);

        when(waitingListRepository.findAll()).thenReturn(waitingLists);
        when(waitingListMapper.toResponseDTOList(waitingLists)).thenReturn(expectedResponses);


        List<WaitingListResponseDTO> result = waitingListService.getAll();


        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(waitingListRepository).findAll();
        verify(waitingListMapper).toResponseDTOList(waitingLists);
    }
    @Test
    void update_WhenExists_ShouldReturnUpdatedResponseDTO() {
        Long id = 1L;
        LocalDate date = LocalDate.now();
        Algorithm algorithm = Algorithm.FIFO;
        Integer capacity = 10;
        String mode = "ONLINE";

        WaitingListRequestDTO requestDto = new WaitingListRequestDTO(date, algorithm, capacity, mode);

        WaitingList waitingList = new WaitingList();
        waitingList.setId(id);
        waitingList.setDate(date);
        waitingList.setAlgorithm(algorithm);
        waitingList.setCapacity(capacity);
        waitingList.setMode(mode);

        WaitingListResponseDTO responseDto = new WaitingListResponseDTO(id, date, algorithm, capacity, mode);

        when(waitingListRepository.existsById(id)).thenReturn(true);
        when(waitingListMapper.toEntity(requestDto)).thenReturn(waitingList);
        when(waitingListRepository.save(any(WaitingList.class))).thenReturn(waitingList);
        when(waitingListMapper.toResponseDTO(waitingList)).thenReturn(responseDto);

        WaitingListResponseDTO result = waitingListService.update(id, requestDto);

        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(waitingListRepository).existsById(id);
        verify(waitingListMapper).toEntity(requestDto);
        verify(waitingListRepository).save(any(WaitingList.class));
        verify(waitingListMapper).toResponseDTO(waitingList);
    }


    @Test
    void update_WhenNotExists_ShouldThrowException() {
        Long id = 1L;
        when(waitingListRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> waitingListService.update(id, requestDto));
        verify(waitingListRepository).existsById(id);
    }

    @Test
    void delete_WhenExists_ShouldReturnTrue() {
        Long id = 1L;
        when(waitingListRepository.existsById(id)).thenReturn(true);
        doNothing().when(waitingListRepository).deleteById(id);

        boolean result = waitingListService.delete(id);

        assertTrue(result);
        verify(waitingListRepository).existsById(id);
        verify(waitingListRepository).deleteById(id);
    }

    @Test
    void delete_WhenNotExists_ShouldReturnFalse() {
        Long id = 1L;
        when(waitingListRepository.existsById(id)).thenReturn(false);

        boolean result = waitingListService.delete(id);

        assertFalse(result);
        verify(waitingListRepository).existsById(id);
        verify(waitingListRepository, never()).deleteById(any());
    }

    @Test


    void findById_WhenExists_ShouldReturnResponseDTO() {
        Long id = 1L;
        LocalDate date = LocalDate.now();
        Algorithm algorithm = Algorithm.FIFO;
        Integer capacity = 10;
        String mode = "ONLINE";

        WaitingList waitingList = new WaitingList();
        waitingList.setId(id);
        waitingList.setDate(date);
        waitingList.setAlgorithm(algorithm);
        waitingList.setCapacity(capacity);
        waitingList.setMode(mode);

        WaitingListResponseDTO responseDto = new WaitingListResponseDTO(id, date, algorithm, capacity, mode);

        when(waitingListRepository.findById(id)).thenReturn(Optional.of(waitingList));
        when(waitingListMapper.toResponseDTO(waitingList)).thenReturn(responseDto);

        WaitingListResponseDTO result = waitingListService.findById(id);

        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(waitingListRepository).findById(id);
        verify(waitingListMapper).toResponseDTO(waitingList);
    }



    @Test
    void findById_WhenNotExists_ShouldThrowException() {
        Long id = 1L;
        when(waitingListRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> waitingListService.findById(id));
        verify(waitingListRepository).findById(id);
    }
}