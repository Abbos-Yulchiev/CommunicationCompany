package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.communicationcompany.entity.USSDCodes;

@RepositoryRestResource(path = "ussdCodes")
public interface UssdCodeRepository extends JpaRepository<USSDCodes, Integer> {
}
