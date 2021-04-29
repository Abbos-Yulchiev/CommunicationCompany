package uz.pdp.communicationcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Services;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.ServicesDTO;
import uz.pdp.communicationcompany.repository.ServicesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceForService {

    final ServicesRepository servicesRepository;

    public ServiceForService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public ApiResponse addServices(ServicesDTO servicesDTO) {

        boolean existsByName = servicesRepository.existsByName(servicesDTO.getName());
        if (existsByName)
            return new ApiResponse("The service Name already exist!", false);

        Services services = new Services();
        services.setDescription(servicesDTO.getDescription());
        services.setPrice(servicesDTO.getPrice());
        servicesDTO.setName(servicesDTO.getName());

        servicesRepository.save(services);
        return new ApiResponse("New service added", false);
    }

    public ApiResponse editService(Integer serviceId, ServicesDTO servicesDTO) {

        Optional<Services> optionalServices = servicesRepository.findById(serviceId);
        if (!optionalServices.isPresent())
            return new ApiResponse("Invalid Service Id", false);

        boolean existsByName = servicesRepository.existsByNameAndIdNot(servicesDTO.getName(), serviceId);
        if (existsByName)
            return new ApiResponse("The service Name already exist!", false);

        Services services = optionalServices.get();
        services.setDescription(servicesDTO.getDescription());
        services.setPrice(servicesDTO.getPrice());
        services.setName(servicesDTO.getName());

        servicesRepository.save(services);
        return new ApiResponse("Service successfully edited", false);
    }

    public List<Services> getServiceList() {
        return servicesRepository.findAll();
    }

    public ApiResponse getServiceById(Integer serviceId) {

        Optional<Services> optionalServices = servicesRepository.findById(serviceId);
        return optionalServices.map(services ->
                new ApiResponse("This is Service", true, services))
                .orElseGet(() -> new ApiResponse("Invalid Service Id", false));
    }

    public ApiResponse deleteService(Integer serviceId) {

        Optional<Services> optionalServices = servicesRepository.findById(serviceId);

        if (!optionalServices.isPresent())
            return new ApiResponse("Invalid Service Id", false);
        servicesRepository.deleteById(serviceId);
        return new ApiResponse("Service successfully deleted", true);
    }
}
