package uz.pdp.communicationcompany.controller;

import com.sun.deploy.nativesandbox.comm.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.USSDCodes;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.UssdCodeDTO;
import uz.pdp.communicationcompany.service.UssdCodesService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ussdCode")
public class UssdCodesController {

    final UssdCodesService ussdCodesService;

    public UssdCodesController(UssdCodesService ussdCodesService) {
        this.ussdCodesService = ussdCodesService;
    }

    //Get all codes in a list
    @GetMapping
    public HttpEntity<?> getUssdCodes() {

        List<USSDCodes> ussdCodesList = ussdCodesService.getUssdCodes();
        return ResponseEntity.ok(ussdCodesList);
    }

    // Get one  ussd code by using Id
    @GetMapping(value = "/api/{ussdCodeId}")
    public HttpEntity<?> getUssdCodeById(@PathVariable Integer ussdCodeId) {

        ApiResponse apiResponse = ussdCodesService.getUssCodeById(ussdCodeId);
        return ResponseEntity.ok(apiResponse);
    }

    // Add new USSD code
    @PostMapping
    public HttpEntity<?> addNewUssdCode(@RequestBody UssdCodeDTO ussdCodeDTO) {

        ApiResponse apiResponse = ussdCodesService.addNewUssdCode(ussdCodeDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    //Editing USSD information
    @PutMapping(value = "/{ussdId}")
    public HttpEntity<?> editUssdCode(@PathVariable Integer ussdId, @RequestBody UssdCodeDTO ussdCodeDTO) {

        ApiResponse apiResponse = ussdCodesService.editUssdCode(ussdId, ussdCodeDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    //DeleteById
    @DeleteMapping(value = "/{ussdId}")
    public HttpEntity<?> deleteUssdCode(@PathVariable Integer ussdId) {

        ApiResponse apiResponse = ussdCodesService.deleteUssdCode(ussdId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
