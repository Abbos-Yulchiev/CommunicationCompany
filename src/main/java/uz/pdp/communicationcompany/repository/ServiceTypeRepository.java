package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.communicationcompany.entity.ServiceType;

@RepositoryRestResource(path = "serviceType")
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
}
