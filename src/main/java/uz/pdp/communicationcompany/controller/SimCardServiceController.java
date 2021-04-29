package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.SimCardService;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.SimCardServiceDTO;
import uz.pdp.communicationcompany.service.ServiceForSimCardService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/forSimCardService")
public class SimCardServiceController {

    final ServiceForSimCardService serviceForSimCardService;

    public SimCardServiceController(ServiceForSimCardService serviceForSimCardService) {
        this.serviceForSimCardService = serviceForSimCardService;
    }

    @GetMapping
    public HttpEntity<?> getSimCardService() {

        List<SimCardService> cardServiceList = serviceForSimCardService.getSimCardService();
        return ResponseEntity.ok(cardServiceList);
    }

    @GetMapping(value = "/{serviceId}")
    public HttpEntity<?> getSimCarById(@PathVariable Integer serviceId) {

        ApiResponse apiResponse = serviceForSimCardService.getSimCarById(serviceId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addNewService(@RequestBody SimCardServiceDTO simCardServiceDTO) {

        ApiResponse apiResponse = serviceForSimCardService.addSimCardService(simCardServiceDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping(value = "/{serviceId}")
    public HttpEntity<?> deleteSimCardService(@PathVariable Integer serviceId) {

        ApiResponse apiResponse = serviceForSimCardService.deleteSimCardService(serviceId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
