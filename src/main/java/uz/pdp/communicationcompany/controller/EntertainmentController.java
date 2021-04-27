package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.Entertainment;
import uz.pdp.communicationcompany.entity.Services;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.EntertainmentDTO;
import uz.pdp.communicationcompany.payload.ServicesDTO;
import uz.pdp.communicationcompany.service.EntertainmentService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/entertainment")
public class EntertainmentController {

    final EntertainmentService entertainmentService;

    public EntertainmentController(EntertainmentService entertainmentService) {
        this.entertainmentService = entertainmentService;
    }

    @PostMapping
    public HttpEntity<?> addEntertainment(@RequestBody EntertainmentDTO entertainmentDTO) {

        ApiResponse apiResponse = entertainmentService.addEntertainment(entertainmentDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/{entertainmentId}")
    public HttpEntity<?> editEntertainment(@PathVariable Integer entertainmentId, @RequestBody EntertainmentDTO entertainmentDTO) {

        ApiResponse apiResponse = entertainmentService.editEntertainment(entertainmentId, entertainmentDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getEntertainmentList() {

        List<Entertainment> entertainmentList = entertainmentService.getEntertainmentList();
        return ResponseEntity.ok(entertainmentList);
    }

    // Get user by id
    @GetMapping(value = "/{entertainmentId}")
    public HttpEntity<?> getEntertainmentById(@PathVariable Integer entertainmentId) {

        ApiResponse apiResponse = entertainmentService.getEntertainmentById(entertainmentId);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(value = "/{entertainmentId}")
    public HttpEntity<?> deleteEntertainment(@PathVariable Integer entertainmentId) {

        ApiResponse apiResponse = entertainmentService.deleteEntertainment(entertainmentId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
