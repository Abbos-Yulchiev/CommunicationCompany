package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.TourniquetHistoryDTO;
import uz.pdp.communicationcompany.service.TourniquetHistoryService;


@RestController
@RequestMapping("/api/tourniquetHistory")
public class TourniquetHistoryController {

    final TourniquetHistoryService service;

    public TourniquetHistoryController(TourniquetHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody TourniquetHistoryDTO historyDTO) {
        ApiResponse apiResponse = service.addHistory(historyDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 409).body(apiResponse);
    }
}
