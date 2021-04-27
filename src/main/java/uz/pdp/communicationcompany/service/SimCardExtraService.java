package uz.pdp.communicationcompany.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.entity.SimCardService;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.SimCardServiceDTO;
import uz.pdp.communicationcompany.repository.SimCardRepository;

import java.util.Set;

@Service
public class SimCardExtraService {

    final SimCardRepository simCardRepository;

    public SimCardExtraService(SimCardRepository simCardRepository) {
        this.simCardRepository = simCardRepository;
    }

    public ApiResponse addSimCardService(SimCardServiceDTO simCardServiceDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2) || role.getId().equals(3)) {
                permission = true;
                break;
            }
        }
       /* if (permission) {

            simCardRepository.findById(simCardServiceDTO.ge)
            SimCardService simCardService = new SimCardService();
            simCardService.setSimCard();
        }*/
        return null;
    }
}
