package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.communicationcompany.entity.USSDCodes;

public interface UssdCodesRepository extends JpaRepository<USSDCodes, Integer> {

    boolean existsByUssdCode(String ussdCode);
    boolean existsByUssdCodeNotAndId(String ussdCode, Integer id);
}
