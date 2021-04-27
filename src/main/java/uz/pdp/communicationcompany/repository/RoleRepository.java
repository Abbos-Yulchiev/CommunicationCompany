package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(RoleName roleName);
}
