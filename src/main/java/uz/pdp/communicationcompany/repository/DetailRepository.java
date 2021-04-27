package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.communicationcompany.entity.Detail;

@RepositoryRestResource(path = "detail")
public interface DetailRepository extends JpaRepository<Detail, Integer> {
}
