package uz.pdp.communicationcompany.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.entity.Entertainment;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.RoleDTO;
import uz.pdp.communicationcompany.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /*public ApiResponse addRole(RoleDTO roleDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(5)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            String roleName = roleDTO.getRoleName().toUpperCase();
            Integer counter = roleRepository.findByRoleNames(roleName);
            if (counter > 0)
                return new ApiResponse("The role already exist!", false);
            else {
                Integer id = roleRepository.getByMaxId();

            }
        }
    }
    public ApiResponse editRole(Integer roleId, RoleDTO roleDTO) {
        return null;
    }*/

    public ApiResponse getRoleById(Integer roleId) {

        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (!optionalRole.isPresent()) return new ApiResponse("Invalid Role Id", false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(5)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            return new ApiResponse("This is role:", true, optionalRole.get());
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public List<Role> getRoleList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(5)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            return roleRepository.findAll();
        } else return null;
    }
}
