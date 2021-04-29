package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.communicationcompany.entity.Company;

@RepositoryRestResource(path = "company")
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
