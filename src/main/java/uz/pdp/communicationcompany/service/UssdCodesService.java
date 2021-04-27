package uz.pdp.communicationcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Services;
import uz.pdp.communicationcompany.entity.USSDCodes;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.UssdCodeDTO;
import uz.pdp.communicationcompany.repository.ServicesRepository;
import uz.pdp.communicationcompany.repository.UssdCodesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UssdCodesService {

    final UssdCodesRepository ussdCodesRepository;
    final ServicesRepository servicesRepository;

    public UssdCodesService(UssdCodesRepository ussdCodesRepository, ServicesRepository servicesRepository) {
        this.ussdCodesRepository = ussdCodesRepository;
        this.servicesRepository = servicesRepository;
    }

    //Get all codes in a list
    public List<USSDCodes> getUssdCodes() {
        return ussdCodesRepository.findAll();
    }

    // Get one  ussd code by using Id
    public ApiResponse getUssCodeById(Integer ussdCodeId) {

        Optional<USSDCodes> optionalUSSDCodes = ussdCodesRepository.findById(ussdCodeId);
        if (!optionalUSSDCodes.isPresent())
            return new ApiResponse("Invalid USSD code!", false);
        return new ApiResponse("This is USSD code:", true, optionalUSSDCodes);
    }

    public ApiResponse addNewUssdCode(UssdCodeDTO ussdCodeDTO) {

        boolean exists = ussdCodesRepository.existsByUssdCode(ussdCodeDTO.getUssdCode());
        if (exists)
            return new ApiResponse("This USSD Code Already exist! Please Check and try again!", false);

        Optional<Services> optionalServices = servicesRepository.findById(ussdCodeDTO.getServicesId());
        if (!optionalServices.isPresent())
            return new ApiResponse("Invalid Service Id", false);

        USSDCodes ussdCodes = new USSDCodes();

        ussdCodes.setUssdCode(ussdCodes.getUssdCode());
        ussdCodes.setDescription(ussdCodes.getDescription());
        ussdCodes.setServices(optionalServices.get());
        ussdCodesRepository.save(ussdCodes);
        return new ApiResponse("New USSD code Successfully added!", true);
    }

    public ApiResponse editUssdCode(Integer ussdId, UssdCodeDTO ussdCodeDTO) {

        Optional<USSDCodes> optionalUSSDCodes = ussdCodesRepository.findById(ussdId);
        if (!optionalUSSDCodes.isPresent())
            return new ApiResponse("Invalid USSD code!", false);

        boolean exists = ussdCodesRepository.existsByUssdCodeNotAndId(ussdCodeDTO.getUssdCode(), ussdId);
        if (!exists)
            return new ApiResponse("This USSD Code Already exist! Please Check and try again!", false);

        Optional<Services> optionalServices = servicesRepository.findById(ussdCodeDTO.getServicesId());
        if (!optionalServices.isPresent())
            return new ApiResponse("Invalid Service Id", false);

        USSDCodes ussdCodes = optionalUSSDCodes.get();
        ussdCodes.setUssdCode(ussdCodeDTO.getUssdCode());
        ussdCodes.setDescription(ussdCodeDTO.getDescription());
        ussdCodes.setServices(optionalServices.get());
        ussdCodesRepository.save(ussdCodes);
        return new ApiResponse("USSD codes saved", true);
    }

    public ApiResponse deleteUssdCode(Integer ussdId) {

        Optional<USSDCodes> optionalUSSDCodes = ussdCodesRepository.findById(ussdId);
        if (!optionalUSSDCodes.isPresent())
            return new ApiResponse("Invalid USSD id!", false);

        ussdCodesRepository.deleteById(ussdId);
        return new ApiResponse("USSD code deleted!", true);
    }
}
