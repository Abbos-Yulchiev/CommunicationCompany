package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.SimCardDTO;
import uz.pdp.communicationcompany.payload.SimCardServiceDTO;
import uz.pdp.communicationcompany.service.ServiceForSimCard;

@RestController
@RequestMapping(value = "/api/simCard")
public class SimCardController {

    final ServiceForSimCard simCardService;

    public SimCardController(ServiceForSimCard simCardService) {
        this.simCardService = simCardService;
    }

    @PostMapping
    public HttpEntity<?> registerSimCard(@RequestBody SimCardDTO simCardDTO) {
        ApiResponse response = simCardService.registerSimCard(simCardDTO);
        return ResponseEntity.status(response.isStatus() ? 201 : 401).body(response);
    }
}
