package uz.pdp.communicationcompany.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.*;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.CompanyBranchDTO;
import uz.pdp.communicationcompany.repository.AddressRepository;
import uz.pdp.communicationcompany.repository.CompanyBranchRepository;
import uz.pdp.communicationcompany.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyBranchService {

    final CompanyBranchRepository companyBranchRepository;
    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;

    public CompanyBranchService(CompanyBranchRepository companyBranchRepository, CompanyRepository companyRepository,
                                AddressRepository addressRepository) {
        this.companyBranchRepository = companyBranchRepository;
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }


    public ApiResponse addCompanyBranch(CompanyBranchDTO companyBranchDTO) {

        Optional<Company> optionalCompany = companyRepository.findById(companyBranchDTO.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Invalid Company Id!", false);

        Optional<Address> optionalAddress = addressRepository.findById(companyBranchDTO.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Invalid Address Id!", false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            CompanyBranch companyBranch = new CompanyBranch();
            companyBranch.setCompany(optionalCompany.get());
            companyBranch.setAddress(optionalAddress.get());
            companyBranchRepository.save(companyBranch);
            return new ApiResponse("New Company Branch successfully registered", true, companyBranch.getId());
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public ApiResponse editCompanyBranch(Integer companyBranchId, CompanyBranchDTO companyBranchDTO) {

        Optional<CompanyBranch> optionalCompanyBranch = companyBranchRepository.findById(companyBranchId);
        if (!optionalCompanyBranch.isPresent()) return new ApiResponse("Invalid Company Branch Id!", false);

        Optional<Company> optionalCompany = companyRepository.findById(companyBranchDTO.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Invalid Company Id!", false);

        Optional<Address> optionalAddress = addressRepository.findById(companyBranchDTO.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Invalid Address Id!", false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            CompanyBranch companyBranch = optionalCompanyBranch.get();
            companyBranch.setCompany(optionalCompany.get());
            companyBranch.setAddress(optionalAddress.get());
            companyBranchRepository.save(companyBranch);
            return new ApiResponse("Company Branch successfully edited", true, companyBranch.getId());
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public List<CompanyBranch> getCompanyBranchList() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            return companyBranchRepository.findAll();
        } else return null;

    }

    public ApiResponse getCompanyBranchById(Integer companyBranchId) {

        Optional<CompanyBranch> optionalCompanyBranch = companyBranchRepository.findById(companyBranchId);
        if (!optionalCompanyBranch.isPresent()) return new ApiResponse("Invalid Company Branch Id!", false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            return new ApiResponse("This is Company Branch", true, optionalCompanyBranch.get());
        } else return new ApiResponse("Your position is not allowed to adding user!", false);

    }

    public ApiResponse deleteCompanyBranch(Integer companyBranchId) {

        Optional<CompanyBranch> optionalCompanyBranch = companyBranchRepository.findById(companyBranchId);
        if (!optionalCompanyBranch.isPresent()) return new ApiResponse("Invalid Company Branch Id!", false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = false;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(1) || role.getId().equals(2)) {
                permission = true;
                break;
            }
        }
        if (permission) {
            companyBranchRepository.deleteById(companyBranchId);
            return new ApiResponse("Company Branch successfully edited", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }
}
