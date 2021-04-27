package uz.pdp.communicationcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.entity.Tourniquet;
import uz.pdp.communicationcompany.entity.TourniquetHistory;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.TourniquetHistoryDTO;
import uz.pdp.communicationcompany.repository.EmployeeRepository;
import uz.pdp.communicationcompany.repository.TourniquetHistoryRepository;
import uz.pdp.communicationcompany.repository.TourniquetRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TourniquetHistoryService {

    final TourniquetHistoryRepository tourniquetHistoryRepository;
    final TourniquetRepository tourniquetRepository;
    final EmployeeRepository employeeRepository;

    public TourniquetHistoryService(TourniquetHistoryRepository tourniquetHistoryRepository,
                                    TourniquetRepository tourniquetRepository,
                                    EmployeeRepository employeeRepository) {
        this.tourniquetHistoryRepository = tourniquetHistoryRepository;
        this.tourniquetRepository = tourniquetRepository;
        this.employeeRepository = employeeRepository;
    }

    public ApiResponse addHistory(TourniquetHistoryDTO historyDTO) {

        Optional<Employee> employeeOptional = employeeRepository.findById(historyDTO.getEmployeeId());
        if (!employeeOptional.isPresent())
            return new ApiResponse("Invalid User Id!", false);

        TourniquetHistory tourniquetHistory = new TourniquetHistory();
        Optional<Tourniquet> optionalTourniquet = tourniquetRepository.findById(historyDTO.getTourniquetId());
        if (!optionalTourniquet.isPresent()) return new ApiResponse("Invalid Tourniquet ID!", false);

        tourniquetHistory.setTourniquet(optionalTourniquet.get());
        tourniquetHistory.setEmployee(employeeOptional.get());

        if (historyDTO.isGoingIn()) {
            if (!optionalTourniquet.get().isStatus())
                return new ApiResponse("You are using the tourniquet illegally!", false);
            tourniquetHistory.setPresentTime(LocalDateTime.now());
            tourniquetHistory.setInOut(true);
        } else {
            if (optionalTourniquet.get().isStatus())
                return new ApiResponse("You are using the tourniquet illegally!", false);
            tourniquetHistory.setPresentTime(LocalDateTime.now());
            tourniquetHistory.setInOut(false);
        }
        tourniquetHistoryRepository.save(tourniquetHistory);
        return new ApiResponse("Input and output saved", true);
    }
}
