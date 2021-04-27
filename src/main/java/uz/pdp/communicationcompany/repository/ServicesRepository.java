package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.communicationcompany.entity.Services;

public interface ServicesRepository extends JpaRepository<Services, Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
