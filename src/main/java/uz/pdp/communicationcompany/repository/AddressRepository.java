package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.communicationcompany.entity.Address;

import java.util.UUID;

@RepositoryRestResource(path = "address")
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
