package org.wora.wainting___list.visitor.infrastructure.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.wainting___list.visitor.application.VisitorService;
import org.wora.wainting___list.visitor.application.impl.VisitorServiceImpl;
import org.wora.wainting___list.visitor.domain.dto.UpdateRequestDto;

@RestController
@RequestMapping("/visitors")
@AllArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    @PutMapping("/{visitorId}/status")
    public ResponseEntity<String> updateVisitorStatus(
            @PathVariable long visitorId,
            @RequestBody UpdateRequestDto updateRequestDto) {
        visitorService.updateStatus(updateRequestDto, visitorId);
        return ResponseEntity.ok("Visitor status updated successfully");

    }
}

