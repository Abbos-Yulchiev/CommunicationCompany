package uz.pdp.communicationcompany.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.*;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.SimCardDTO;
import uz.pdp.communicationcompany.repository.*;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class ServiceForSimCard {


    final SimCardRepository simCardRepository;
    final EmployeeRepository employeeRepository;
    final UserRepository userRepository;
    final CompanyRepository companyRepository;
    final ServicesRepository serviceRepository;
    final SimCardServiceRepository simCardServiceRepository;
    final TariffsRepository tariffRepository;
    final SimCardTariffRepository simCardTariffRepository;


    public ServiceForSimCard(SimCardRepository simCardRepository, UserRepository userRepository,
                             EmployeeRepository employeeRepository, UserRepository userRepository1,
                             CompanyRepository companyRepository, ServicesRepository serviceRepository,
                             SimCardServiceRepository simCardServiceRepository, TariffsRepository tariffRepository,
                             SimCardTariffRepository simCardTariffRepository) {
        this.simCardRepository = simCardRepository;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository1;
        this.companyRepository = companyRepository;
        this.serviceRepository = serviceRepository;
        this.simCardServiceRepository = simCardServiceRepository;
        this.tariffRepository = tariffRepository;
        this.simCardTariffRepository = simCardTariffRepository;
    }

    public ApiResponse registerSimCard(SimCardDTO simCardDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(3)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            SimCard simCard = new SimCard();
            String generatePhoneNumber = generatePhoneNumber(simCardDTO.getCode().toString());
            simCard.setPhoneNumber(generatePhoneNumber);

            Optional<Users> optionalUsers = userRepository.findById(simCardDTO.getCustomerId());
            if (!optionalUsers.isPresent())
                return new ApiResponse("Invalid User Id", false);
            simCard.setUsers(optionalUsers.get());

            Optional<Company> optionalCompany = companyRepository.findById(simCardDTO.getCompanyId());
            if (!optionalCompany.isPresent())
                return new ApiResponse("Invalid Company", false);
            simCard.setCompany(optionalCompany.get());

            simCardRepository.save(simCard);
            return new ApiResponse("Sim Card saved! Phone number: " + generatePhoneNumber + " to " + optionalUsers.get().getFirstName(), true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }


    private String generatePhoneNumber(String code) {
        String nums = "0123456789";
        Random random = new Random();

        String result = "";

        for (int i = 1; i <= 7; i++) {
            char c = nums.charAt(random.nextInt(10));
            result = result + c;

            if (i == 7) {
                String full_phone_number = "+998" + code + result;
                boolean b = simCardRepository.existsByPhoneNumber(full_phone_number);
                if (b) {
                    i = 0;
                } else {
                    return full_phone_number;
                }
            }
        }
        return "";
    }
}
