package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.SimCardServiceDTO;
import uz.pdp.communicationcompany.service.SimCardExtraService;

@RestController
@RequestMapping(value = "/api/simCard")
public class SimCardController {

    final SimCardExtraService simCardExtraService;

    public SimCardController(SimCardExtraService simCardExtraService) {
        this.simCardExtraService = simCardExtraService;
    }

    @PostMapping
    public HttpEntity<?> addSimCard(@RequestBody SimCardServiceDTO simcardServiceDTO) {

        ApiResponse apiResponse = simCardExtraService.addSimCardService(simcardServiceDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
