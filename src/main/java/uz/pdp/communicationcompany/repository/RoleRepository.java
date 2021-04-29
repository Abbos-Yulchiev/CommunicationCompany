package uz.pdp.communicationcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(RoleName roleName);

    @Query(value = "SELECT  count(*) from role where role_name =:name", nativeQuery = true)
    Integer findByRoleNames(String name);

    /**
     * Get max id and add next role

     @Query(value = "select id from role  order by role.id desc limit 1")
     Integer getByMaxId();

     @Query(value = "INSERT INTO role(id,role_name) values (:id,:name) returning id;")
     Integer getLastId(String id, String name);*/

}
