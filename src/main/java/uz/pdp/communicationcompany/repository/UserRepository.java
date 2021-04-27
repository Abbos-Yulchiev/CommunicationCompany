package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.communicationcompany.entity.Users;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

}
