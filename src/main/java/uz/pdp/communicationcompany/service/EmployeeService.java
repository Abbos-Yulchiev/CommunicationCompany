package uz.pdp.communicationcompany.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Address;
import uz.pdp.communicationcompany.entity.CompanyBranch;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.EmployeeDTO;
import uz.pdp.communicationcompany.repository.AddressRepository;
import uz.pdp.communicationcompany.repository.CompanyBranchRepository;
import uz.pdp.communicationcompany.repository.EmployeeRepository;
import uz.pdp.communicationcompany.repository.RoleRepository;

import java.util.*;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final CompanyBranchRepository companyBranchRepository;
    final RoleRepository roleRepository;
    final AddressRepository addressRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyBranchRepository companyBranchRepository,
                           RoleRepository roleRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.companyBranchRepository = companyBranchRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
    }

    public ApiResponse addEmployee(EmployeeDTO employeeDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee auth = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = auth.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2) || role.getId().equals(5)) {
                permission = true;
                break;
            }
        }

        if (permission) {
            Set<CompanyBranch> companyBranches = new HashSet<>();
            Set<Role> roleSet = new HashSet<>();

            /* Placing Branches to Employee by Set()*/
            for (Integer branchId : employeeDTO.getCompanyBranchListId()) {
                Optional<CompanyBranch> optionalCompanyBranch = companyBranchRepository.findById(branchId);
                if (!optionalCompanyBranch.isPresent()) {
                    return new ApiResponse("Invalid Branch Id", false);
                }
                companyBranches.add(optionalCompanyBranch.get());
            }

            /* Placing Employee Roles */
            for (Integer roleId : employeeDTO.getRolesListId()) {
                Optional<Role> optionalRole = roleRepository.findById(roleId);
                if (!optionalRole.isPresent()) {
                    return new ApiResponse("Invalid Role Id given!", false);
                }
                roles.add(optionalRole.get());
            }

            Address address = new Address();
            address.setCityName(employeeDTO.getAddress().getCityName());
            address.setStreetName(employeeDTO.getAddress().getStreetName());

            Employee newEmployee = new Employee();
            newEmployee.setFirstName(employeeDTO.getFirstName());
            newEmployee.setLastName(employeeDTO.getLastName());
            newEmployee.setEmail(employeeDTO.getEmail());
            newEmployee.setPassword(employeeDTO.getPassword());
            newEmployee.setRoles(roles);
            newEmployee.setCompanyBranch(companyBranches);
            newEmployee.setAddress(address);
            addressRepository.save(address);
            employeeRepository.save(newEmployee);
            return new ApiResponse("New " + roles + " successfully added", true, newEmployee.getId());
        } else {
            return new ApiResponse("Your position is not allowed to adding user!", false);

        }
    }

    public ApiResponse editEmployee(UUID employeeId, EmployeeDTO employeeDTO) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (!optionalEmployee.isPresent())
            return new ApiResponse("Invalid Employee Id", false);

        Set<CompanyBranch> companyBranches = new HashSet<>();
        Set<Role> roles = new HashSet<>();

        /* Placing Branches to Employee by Set()*/
        for (Integer branchId : employeeDTO.getCompanyBranchListId()) {
            Optional<CompanyBranch> optionalCompanyBranch = companyBranchRepository.findById(branchId);
            if (!optionalCompanyBranch.isPresent()) {
                return new ApiResponse("Invalid Branch Id", false);
            }
            companyBranches.add(optionalCompanyBranch.get());
        }

        /* Placing Employee Roles */
        for (Integer roleId : employeeDTO.getRolesListId()) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if (!optionalRole.isPresent()) {
                return new ApiResponse("Invalid Role Id given!", false);
            }
            roles.add(optionalRole.get());
        }
        Address address = optionalEmployee.get().getAddress();
        address.setCityName(employeeDTO.getAddress().getCityName());
        address.setStreetName(employeeDTO.getAddress().getStreetName());

        Employee employee = optionalEmployee.get();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setRoles(roles);
        employee.setCompanyBranch(companyBranches);
        employee.setAddress(address);
        addressRepository.save(address);
        employeeRepository.save(employee);
        return new ApiResponse("Employee successfully edited", true, employee.getId());
    }

    public ApiResponse getEmployee(UUID employeeId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)
                    || role.getId().equals(4) || role.getId().equals(5)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
            if (!optionalEmployee.isPresent())
                return new ApiResponse("Invalid Employee Id", false);
            return new ApiResponse("This is Employee", true, optionalEmployee);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public List<Employee> getEmployeeList() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)
                    || role.getId().equals(4) || role.getId().equals(5)) {
                permission = true;
                break;
            }
        }
        if (permission)
            return employeeRepository.findAll();
        else return null;
    }
}
