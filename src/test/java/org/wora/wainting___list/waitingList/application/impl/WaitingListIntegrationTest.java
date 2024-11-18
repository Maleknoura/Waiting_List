package org.wora.wainting___list.waitingList.application.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.wora.wainting___list.waitingList.domain.enums.Algorithm;
import org.wora.wainting___list.waitingList.domain.repository.WaitingListRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class WaitingListIntegrationTest {

    @Autowired
    private WaitingListServiceImpl waitingListService;

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Test
    void create_ShouldPersistWaitingListAndReturnResponseDTO() {
        WaitingListRequestDTO requestDto = new WaitingListRequestDTO(
                LocalDate.now(),
                Algorithm.FIFO,
                100,
                "Online"
        );

        WaitingListResponseDTO responseDto = waitingListService.create(requestDto);

        assertNotNull(responseDto);
        assertEquals(requestDto.date(), responseDto.date());
        assertEquals(requestDto.algorithm(), responseDto.algorithm());
        assertEquals(requestDto.capacity(), responseDto.capacity());
        assertEquals(requestDto.mode(), responseDto.mode());

        assertTrue(waitingListRepository.existsById(responseDto.id()));
    }
}
