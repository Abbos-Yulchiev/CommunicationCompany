package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.communicationcompany.entity.Details;

@RepositoryRestResource(path = "detail")
public interface DetailRepository extends JpaRepository<Details, Integer> {
}
