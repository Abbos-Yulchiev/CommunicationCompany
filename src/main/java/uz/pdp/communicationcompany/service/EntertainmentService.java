package uz.pdp.communicationcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Entertainment;
import uz.pdp.communicationcompany.entity.Services;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.EntertainmentDTO;
import uz.pdp.communicationcompany.payload.ServicesDTO;
import uz.pdp.communicationcompany.repository.EntertainmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntertainmentService {

    final EntertainmentRepository entertainmentRepository;

    public EntertainmentService(EntertainmentRepository entertainmentRepository) {
        this.entertainmentRepository = entertainmentRepository;
    }

    public ApiResponse addEntertainment(EntertainmentDTO entertainmentDTO) {

        boolean existsByName = entertainmentRepository.existsByName(entertainmentDTO.getName());
        if (existsByName)
            return new ApiResponse("The Entertains already exist!", false);

        Entertainment entertainment = new Entertainment();

        entertainment.setName(entertainmentDTO.getName());
        entertainment.setDescription(entertainmentDTO.getDescription());
        entertainment.setPrice(entertainmentDTO.getPrice());
        entertainment.setServices(entertainmentDTO.getServices());


        entertainmentRepository.save(entertainment);
        return new ApiResponse("New entertainment added", false);
    }

    public ApiResponse editEntertainment(Integer entertainmentId, EntertainmentDTO entertainmentDTO) {

        Optional<Entertainment> optionalEntertainment = entertainmentRepository.findById(entertainmentId);
        if (!optionalEntertainment.isPresent())
            return new ApiResponse("Invalid Entertainment Id", false);

        boolean existsByName = entertainmentRepository.existsByNameAndIdNot(entertainmentDTO.getName(), entertainmentId);
        if (existsByName)
            return new ApiResponse("The service Name already exist!", false);

        Entertainment entertainment = optionalEntertainment.get();
        entertainment.setPrice(entertainmentDTO.getPrice());
        entertainment.setDescription(entertainmentDTO.getDescription());
        entertainment.setName(entertainmentDTO.getName());
        entertainment.setServices(entertainmentDTO.getServices());

        entertainmentRepository.save(entertainment);
        return new ApiResponse("Entertainment successfully edited", false);
    }

    public List<Entertainment> getEntertainmentList() {
        return entertainmentRepository.findAll();
    }

    public ApiResponse getEntertainmentById(Integer entertainmentId) {

        Optional<Entertainment> optionalEntertainment = entertainmentRepository.findById(entertainmentId);
        return optionalEntertainment.map(entertainment ->
                new ApiResponse("This is Service", true, entertainment))
                .orElseGet(() -> new ApiResponse("Invalid Entertainment Id", false));
    }

    public ApiResponse deleteEntertainment(Integer entertainmentId) {

        Optional<Entertainment> optionalEntertainment = entertainmentRepository.findById(entertainmentId);
        if (!optionalEntertainment.isPresent())
            return new ApiResponse("Invalid Entertainment Id", false);
        entertainmentRepository.deleteById(entertainmentId);
        return new ApiResponse("Entertainment successfully deleted", true);
    }
}
