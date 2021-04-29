package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.communicationcompany.entity.SimCard;

import java.util.Optional;

public interface SimCardRepository extends JpaRepository<SimCard, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<SimCard> findByPhoneNumber(String phoneNumber);
}
