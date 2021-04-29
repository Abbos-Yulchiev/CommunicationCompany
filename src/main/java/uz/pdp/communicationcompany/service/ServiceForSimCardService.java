package uz.pdp.communicationcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.repository.SimCardServiceRepository;

@Service
public class ServiceForSimCardService {

    final SimCardServiceRepository simCardServiceRepository;

    public ServiceForSimCardService(SimCardServiceRepository simCardServiceRepository) {
        this.simCardServiceRepository = simCardServiceRepository;
    }
}
