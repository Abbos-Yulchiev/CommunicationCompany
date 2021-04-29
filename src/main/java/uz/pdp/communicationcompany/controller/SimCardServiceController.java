package uz.pdp.communicationcompany.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.communicationcompany.service.ServiceForSimCardService;

@RestController
@RequestMapping(value = "/api/forSimCardService")
public class SimCardServiceController {

    final ServiceForSimCardService serviceForSimCardService;

    public SimCardServiceController(ServiceForSimCardService serviceForSimCardService) {
        this.serviceForSimCardService = serviceForSimCardService;
    }

}
