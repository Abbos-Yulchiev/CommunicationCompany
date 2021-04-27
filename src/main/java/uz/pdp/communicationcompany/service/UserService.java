package uz.pdp.communicationcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Address;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.entity.Users;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.UserDTO;
import uz.pdp.communicationcompany.repository.AddressRepository;
import uz.pdp.communicationcompany.repository.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;
    final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public Page<Users> getUsersByPage(Integer page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = true;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(7)) {
                permission = false;
                break;
            }
        }

        if (permission) {
            Pageable pageable = PageRequest.of(page, 30);
            return userRepository.findAll(pageable);
        } else return null;
    }

    public ApiResponse getUserById(UUID userId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = true;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (role.getId().equals(7)) {
                permission = false;
                break;
            }
        }
        if (permission) {
            Optional<Users> optionalUsers = userRepository.findById(userId);
            return optionalUsers.map(users ->
                    new ApiResponse("This is user: ", true, users)).orElseGet(() ->
                    new ApiResponse("Invalid user Id chosen! Such user do not exist!", false));
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public ApiResponse addUser(UserDTO userDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = true;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (!role.getId().equals(1) && !role.getId().equals(7)) {
                permission = false;
                break;
            }
        }
        if (permission) {
            Address address = new Address();
            address.setStreetName(userDTO.getAddress().getStreetName());
            address.setCityName(userDTO.getAddress().getCityName());
            Users users = new Users();
            users.setFirstName(userDTO.getFirstName());
            users.setLastName(userDTO.getLastName());
            users.setAddress(address);
            addressRepository.save(address);
            userRepository.save(users);
            return new ApiResponse("New User successfully added signed.", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public ApiResponse editUser(UUID userId, UserDTO userDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = true;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (!role.getId().equals(1) && !role.getId().equals(7)) {
                permission = false;
                break;
            }
        }
        if (permission) {
            Optional<Users> optionalUsers = userRepository.findById(userId);
            if (!optionalUsers.isPresent())
                return new ApiResponse("Invalid User Id!", false);

            Address address = new Address();
            address.setStreetName(userDTO.getAddress().getStreetName());
            address.setCityName(userDTO.getAddress().getCityName());

            Users users = optionalUsers.get();
            users.setFirstName(userDTO.getFirstName());
            users.setLastName(userDTO.getLastName());
            users.setAddress(address);
            addressRepository.save(address);
            userRepository.save(users);
            return new ApiResponse("New User successfully added signed.", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }

    public ApiResponse deleteUser(UUID userId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();
        boolean permission = true;
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            if (!role.getId().equals(1) && !role.getId().equals(7)) {
                permission = false;
                break;
            }
        }
        if (permission) {

            Optional<Users> optionalUsers = userRepository.findById(userId);
            if (!optionalUsers.isPresent())
                return new ApiResponse("Invalid User Id!", false);

            userRepository.deleteById(userId);
            return new ApiResponse("User successfully deleted", true);
        } else return new ApiResponse("Your position is not allowed to adding user!", false);
    }
}
