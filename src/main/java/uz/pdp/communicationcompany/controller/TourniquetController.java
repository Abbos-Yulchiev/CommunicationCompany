package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.TourniquetDTO;
import uz.pdp.communicationcompany.service.TourniquetService;

@RestController
@RequestMapping("/api/tourniquet")
public class TourniquetController {

    final TourniquetService tourniquetService;

    public TourniquetController(TourniquetService tourniquetService) {
        this.tourniquetService = tourniquetService;
    }


    @PostMapping
    public HttpEntity<?> add(@RequestBody TourniquetDTO tourniquetDTO) {

        ApiResponse apiResponse = tourniquetService.add(tourniquetDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody TourniquetDTO tourniquetDTO) {

        ApiResponse apiResponse = tourniquetService.edit(id, tourniquetDTO);
        return ResponseEntity.status(apiResponse.isStatus()? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse response = tourniquetService.delete(id);
        return ResponseEntity.status(response.isStatus() ? 202 : 409).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(tourniquetService.getAll());
    }
}
