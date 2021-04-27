package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.Employee;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.EmployeeDTO;
import uz.pdp.communicationcompany.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public HttpEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDTO) {

        ApiResponse apiResponse = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/{employeeId}")
    public HttpEntity<?> editEmployee(@PathVariable UUID employeeId, EmployeeDTO employeeDTO) {

        ApiResponse apiResponse = employeeService.editEmployee(employeeId, employeeDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    /*Getting one employee by id*/
    @GetMapping(value = "/{employeeId}")
    public HttpEntity<?> getEmployee(@PathVariable UUID employeeId) {

        ApiResponse apiResponse = employeeService.getEmployee(employeeId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    /*Getting Employee List*/
    @GetMapping
    public HttpEntity<?> getEmployeeList() {

        List<Employee> employeeList = employeeService.getEmployeeList();
        return ResponseEntity.ok(employeeList);
    }

}
