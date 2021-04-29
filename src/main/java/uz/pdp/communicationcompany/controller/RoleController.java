package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.Entertainment;
import uz.pdp.communicationcompany.entity.Role;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.EntertainmentDTO;
import uz.pdp.communicationcompany.payload.RoleDTO;
import uz.pdp.communicationcompany.service.RoleService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /*@PostMapping
    public HttpEntity<?> addRole(@RequestBody RoleDTO roleDTO) {

        ApiResponse apiResponse = roleService.addRole(roleDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/{roleId}")
    public HttpEntity<?> editRole(@PathVariable Integer roleId, @RequestBody RoleDTO roleDTO) {

        ApiResponse apiResponse = roleService.editRole(roleId, roleDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }*/

    @GetMapping
    public HttpEntity<?> getRoleList() {

        List<Role> entertainmentList = roleService.getRoleList();
        return ResponseEntity.ok(entertainmentList);
    }

    // Get user by id
    @GetMapping(value = "/{roleId}")
    public HttpEntity<?> getRoleById(@PathVariable Integer roleId) {

        ApiResponse apiResponse = roleService.getRoleById(roleId);
        return ResponseEntity.ok(apiResponse);
    }

    /*@DeleteMapping(value = "/{roleId}")
    public HttpEntity<?> deleteRole(@PathVariable Integer roleId) {

        ApiResponse apiResponse = roleService.deleteRole(roleId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }*/
}
