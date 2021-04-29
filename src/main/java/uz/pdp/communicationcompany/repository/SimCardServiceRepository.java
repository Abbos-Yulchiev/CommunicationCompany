package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.communicationcompany.entity.Services;
import uz.pdp.communicationcompany.entity.SimCard;
import uz.pdp.communicationcompany.entity.SimCardService;

public interface SimCardServiceRepository extends JpaRepository<SimCardService, Integer> {

    boolean existsByServicesAndSimCard(Services services, SimCard simCard);
}
