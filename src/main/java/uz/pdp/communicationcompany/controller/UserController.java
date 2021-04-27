package uz.pdp.communicationcompany.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.Users;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.UserDTO;
import uz.pdp.communicationcompany.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Getting all users by using pageable
    @GetMapping
    public HttpEntity<?> getUsersByPage(@PathVariable Integer page) {

        Page<Users> usersByPage = userService.getUsersByPage(page);
        return ResponseEntity.ok(usersByPage);
    }

    // Get user by id
    @GetMapping(value = "/{userId}")
    public HttpEntity<?> getUserById(@PathVariable UUID userId) {

        ApiResponse apiResponse = userService.getUserById(userId);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addUser(@RequestBody UserDTO userDTO) {

        ApiResponse apiResponse = userService.addUser(userDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/{userId}")
    public HttpEntity<?> editUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO) {

        ApiResponse apiResponse = userService.editUser(userId, userDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping(value = "/{userId}")
    public HttpEntity<?> deleteUser(@PathVariable UUID userId) {

        ApiResponse apiResponse = userService.deleteUser(userId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
