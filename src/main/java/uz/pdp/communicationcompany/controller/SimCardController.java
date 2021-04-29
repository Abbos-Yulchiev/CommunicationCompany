package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.SimCardDTO;
import uz.pdp.communicationcompany.service.ServiceForSimCard;

@RestController
@RequestMapping(value = "/api/simCard")
public class SimCardController {

    final ServiceForSimCard simCardExtraService;

    public SimCardController(ServiceForSimCard simCardExtraService) {
        this.simCardExtraService = simCardExtraService;
    }


    @PostMapping
    public HttpEntity<?> addSimCard(@RequestBody SimCardDTO simcardDTO) {

        ApiResponse apiResponse = simCardExtraService.addSimCardService(simcardDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
