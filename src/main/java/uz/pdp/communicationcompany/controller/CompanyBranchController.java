package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.CompanyBranch;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.CompanyBranchDTO;
import uz.pdp.communicationcompany.service.CompanyBranchService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/companyBranch")
public class CompanyBranchController {

    final CompanyBranchService companyBranchService;

    public CompanyBranchController(CompanyBranchService companyBranchService) {
        this.companyBranchService = companyBranchService;
    }

    @PostMapping
    public HttpEntity<?> addCompanyBranch(@RequestBody CompanyBranchDTO companyBranchDTO) {

        ApiResponse apiResponse = companyBranchService.addCompanyBranch(companyBranchDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/{companyBranchId}")
    public HttpEntity<?> editCompanyBranch(@PathVariable Integer companyBranchId, @RequestBody CompanyBranchDTO companyBranchDTO) {

        ApiResponse apiResponse = companyBranchService.editCompanyBranch(companyBranchId, companyBranchDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getCompanyBranchList() {

        List<CompanyBranch> entertainmentList = companyBranchService.getCompanyBranchList();
        return ResponseEntity.ok(entertainmentList);
    }

    // Get user by id
    @GetMapping(value = "/{companyBranchId}")
    public HttpEntity<?> getCompanyBranchById(@PathVariable Integer companyBranchId) {

        ApiResponse apiResponse = companyBranchService.getCompanyBranchById(companyBranchId);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(value = "/{companyBranchId}")
    public HttpEntity<?> deleteCompanyBranch(@PathVariable Integer companyBranchId) {

        ApiResponse apiResponse = companyBranchService.deleteCompanyBranch(companyBranchId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
