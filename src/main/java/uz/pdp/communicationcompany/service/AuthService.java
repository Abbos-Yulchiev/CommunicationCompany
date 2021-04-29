package uz.pdp.communicationcompany.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.entity.Users;
import uz.pdp.communicationcompany.entity.enums.RoleName;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.LoginDTO;
import uz.pdp.communicationcompany.payload.RegisterDTO;
import uz.pdp.communicationcompany.repository.EmployeeRepository;
import uz.pdp.communicationcompany.repository.RoleRepository;
import uz.pdp.communicationcompany.repository.UserRepository;
import uz.pdp.communicationcompany.security.JwtProvider;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    final RoleRepository roleRepository;
    final EmployeeRepository employeeRepository;
    final PasswordEncoder passwordEncoder;
    final JavaMailSender javaMailSender;
    final AuthenticationManager authenticationManager;
    final JwtProvider jwtProvider;

    public AuthService(RoleRepository roleRepository, EmployeeRepository employeeRepository,
                       PasswordEncoder passwordEncoder, JavaMailSender javaMailSender,
                       AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }


    public ApiResponse registerEmployee(RegisterDTO registerDTO) {

        boolean existsByEmail = employeeRepository.existsByEmail(registerDTO.getEmail());
        if (existsByEmail) {
            return new ApiResponse("Enter another Email this email already exist!", false);
        }

        Employee employee = new Employee();
        employee.setFirstName(registerDTO.getFirstName());
        employee.setLastName(registerDTO.getLastName());

        employee.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        employee.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.EMPLOYEE)));

        employee.setEmailCode(UUID.randomUUID().toString());

        employeeRepository.save(employee);

        //Emailga habar yuborush methofdinin chaqiryapmiz
        sendEmail(employee.getEmail(), employee.getEmailCode());
        return new ApiResponse("Successfully registered. For activation verify email message.", true, employee);
    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Test@pdp.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Account Header");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Tasdiqlang</a>");
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ApiResponse verifyEmail(String emailCode, String email) {

        Optional<Employee> optionalEmployee = employeeRepository.findByEmailAndEmailCode(email, emailCode);
        if (optionalEmployee.isPresent()) {

            Employee employee = optionalEmployee.get();
            employee.setEnabled(true);
            employee.setEmailCode(null);
            employeeRepository.save(employee);
            return new ApiResponse("Account successfully verified", true);
        }
        return new ApiResponse("Account already verified!", false);
    }


    public ApiResponse login(LoginDTO loginDTO) {

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()));

            Employee user = (Employee) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDTO.getUsername(), user.getRoles());
            return new ApiResponse("This is token", true, token);
        } catch (BadCredentialsException badCredentialsException) {

            return new ApiResponse("Login or password is incorrect", false);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
    }

}
