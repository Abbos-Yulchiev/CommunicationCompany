package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.communicationcompany.entity.SimCard;

public interface SimCardRepository extends JpaRepository<SimCard, Integer> {
}
