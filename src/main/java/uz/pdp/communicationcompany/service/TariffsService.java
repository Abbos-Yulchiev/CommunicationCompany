package uz.pdp.communicationcompany.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.entity.Tariffs;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.TariffsDTO;
import uz.pdp.communicationcompany.repository.TariffsRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TariffsService {

    final TariffsRepository tariffsRepository;

    public TariffsService(TariffsRepository tariffsRepository) {
        this.tariffsRepository = tariffsRepository;
    }

    public List<Tariffs> getTariffsList() {
        return tariffsRepository.findAll();

    }

    public Tariffs geTariff(Integer tariffsId) {

        Optional<Tariffs> optionalTariffs = tariffsRepository.findById(tariffsId);
        return optionalTariffs.orElse(null);
    }

    public ApiResponse addTariff(TariffsDTO tariffsDTO) {

        String tariffName = tariffsDTO.getName().toLowerCase();

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
        if (permission) {
            boolean existsByName = tariffsRepository.existsByName(tariffName);
            if (existsByName)
                return new ApiResponse("The tariff name already exist!", false);

            Tariffs tariffs = new Tariffs();
            tariffs.setDescription(tariffsDTO.getDescription());
            tariffs.setDetail(tariffsDTO.getDetail());
            tariffs.setName(tariffName);
            tariffs.setPhysicalPerson(tariffsDTO.isPhysicalPerson());
            tariffsRepository.save(tariffs);
            return new ApiResponse("New Tariff successfully added", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public ApiResponse editTariff(Integer tariffId, TariffsDTO tariffsDTO) {

        Optional<Tariffs> optionalTariffs = tariffsRepository.findById(tariffId);
        if (!optionalTariffs.isPresent()) return new ApiResponse("Invalid Tariff Id!", false);

        String tariffName = tariffsDTO.getName().toLowerCase();

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
        if (permission) {
            boolean existsByName = tariffsRepository.existsByName(tariffName);
            if (existsByName)
                return new ApiResponse("The tariff name already exist!", false);

            Tariffs tariffs = optionalTariffs.get();
            tariffs.setDescription(tariffsDTO.getDescription());
            tariffs.setDetail(tariffsDTO.getDetail());
            tariffs.setName(tariffName);
            tariffs.setPhysicalPerson(tariffsDTO.isPhysicalPerson());
            tariffsRepository.save(tariffs);
            return new ApiResponse("Tariff successfully edited", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);

    }

    public ApiResponse deleteTariff(Integer tariffId) {

        Optional<Tariffs> optionalTariffs = tariffsRepository.findById(tariffId);
        if (!optionalTariffs.isPresent()) return new ApiResponse("Invalid Tariff Id!", false);

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
        if (permission) {
            tariffsRepository.deleteById(tariffId);
            return new ApiResponse("Tariff successfully deleted", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }
}
