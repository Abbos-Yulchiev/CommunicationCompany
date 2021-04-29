package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.Services;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.ServicesDTO;
import uz.pdp.communicationcompany.service.ServiceForService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/service")
public class ServiceController {


    final ServiceForService serviceForService;

    public ServiceController(ServiceForService serviceForService) {
        this.serviceForService = serviceForService;
    }


    @PostMapping
    public HttpEntity<?> addService(@RequestBody ServicesDTO servicesDTO) {

        ApiResponse apiResponse = serviceForService.addServices(servicesDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/{userId}")
    public HttpEntity<?> editService(@PathVariable Integer userId, @RequestBody ServicesDTO servicesDTO) {

        ApiResponse apiResponse = serviceForService.editService(userId, servicesDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getServiceList() {

        List<Services> usersByPage = serviceForService.getServiceList();
        return ResponseEntity.ok(usersByPage);
    }

    // Get user by id
    @GetMapping(value = "/{serviceId}")
    public HttpEntity<?> getServiceById(@PathVariable Integer serviceId) {

        ApiResponse apiResponse = serviceForService.getServiceById(serviceId);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(value = "/{serviceId}")
    public HttpEntity<?> deleteUser(@PathVariable Integer serviceId) {

        ApiResponse apiResponse = serviceForService.deleteService(serviceId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
