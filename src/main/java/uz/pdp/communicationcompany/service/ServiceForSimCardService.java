package uz.pdp.communicationcompany.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.*;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.SimCardServiceDTO;
import uz.pdp.communicationcompany.repository.ServicesRepository;
import uz.pdp.communicationcompany.repository.SimCardRepository;
import uz.pdp.communicationcompany.repository.SimCardServiceRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceForSimCardService {

    final SimCardServiceRepository simCardServiceRepository;
    final SimCardRepository simCardRepository;
    final ServicesRepository servicesRepository;

    public ServiceForSimCardService(SimCardServiceRepository simCardServiceRepository, SimCardRepository simCardRepository,
                                    ServicesRepository servicesRepository) {
        this.simCardServiceRepository = simCardServiceRepository;
        this.simCardRepository = simCardRepository;
        this.servicesRepository = servicesRepository;
    }

    public List<SimCardService> getSimCardService() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)
                    || role.getId().equals(3) || role.getId().equals(5)
                    || role.getId().equals(6)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            return simCardServiceRepository.findAll();
        } else return null;
    }

    public ApiResponse getSimCarById(Integer serviceId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)
                    || role.getId().equals(3) || role.getId().equals(5)
                    || role.getId().equals(6)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            Optional<SimCardService> service = simCardServiceRepository.findById(serviceId);
            if (!service.isPresent())
                return new ApiResponse("Invalid Service Id", false);
            return new ApiResponse("This is service:", true, service);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public ApiResponse addSimCardService(SimCardServiceDTO simCardServiceDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)
                    || role.getId().equals(3)) {
                permission = true;
                break;
            }
        }
        if (permission) {

            Optional<SimCard> optionalSimCard = simCardRepository.findById(simCardServiceDTO.getSimCardId());
            if (!optionalSimCard.isPresent())
                return new ApiResponse("Invalid Sim Card Id!", false);

            Optional<Services> optionalServices = servicesRepository.findById(simCardServiceDTO.getServicesId());
            if (!optionalServices.isPresent())
                return new ApiResponse("Invalid Service Id!", false);

            boolean exists = simCardServiceRepository.existsByServicesAndSimCard(optionalServices.get(), optionalSimCard.get());
            if (exists)
                return new ApiResponse("The service already connected!", false);

            //Xizmat uchun hisobdan pul yechish
            double service_price = optionalServices.get().getPrice();
            double balance = optionalSimCard.get().getBalance();
            if (service_price >= balance) {
                double newBalance = balance - service_price;
                optionalSimCard.get().setBalance(newBalance);
                simCardRepository.save(optionalSimCard.get());
            } else {
                return new ApiResponse("Your balance is not sufficient for this service!", false);
            }

            SimCardService simCardService = new SimCardService();
            simCardService.setSimCard(optionalSimCard.get());

            simCardService.setServices(optionalServices.get());
            simCardService.setStartDate(simCardServiceDTO.getStartDate());
            simCardService.setEndDate(simCardServiceDTO.getEndDate());
            simCardService.setStatus(simCardServiceDTO.getStatus());
            simCardServiceRepository.save(simCardService);
            return new ApiResponse("New service Connected", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public ApiResponse deleteSimCardService(Integer serviceId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)
                    || role.getId().equals(3)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            Optional<SimCardService> optional = simCardServiceRepository.findById(serviceId);
            if (!optional.isPresent())
                return new ApiResponse("Invalid Id!", false);
            simCardServiceRepository.deleteById(serviceId);
            return new ApiResponse("Sim Card service Deleted", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }
}
