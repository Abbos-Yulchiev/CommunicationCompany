package uz.pdp.communicationcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.communicationcompany.entity.Tariffs;
import uz.pdp.communicationcompany.payload.ApiResponse;
import uz.pdp.communicationcompany.payload.TariffsDTO;
import uz.pdp.communicationcompany.service.TariffsService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tariffs")
public class TariffsController {

    final TariffsService tariffsService;

    public TariffsController(TariffsService tariffsService) {
        this.tariffsService = tariffsService;
    }

    @GetMapping
    public HttpEntity<?> getTariffs() {

        List<Tariffs> tariffsList = tariffsService.getTariffsList();
        return ResponseEntity.ok(tariffsList);
    }

    @GetMapping(value = "/{tariffsId}")
    public HttpEntity<?> getTariff(@PathVariable Integer tariffsId) {

        Tariffs tariffs = tariffsService.geTariff(tariffsId);
        return ResponseEntity.ok(tariffs);
    }

    @PostMapping
    public HttpEntity<?> addTariffs(@RequestBody TariffsDTO tariffsDTO) {

        ApiResponse apiResponse = tariffsService.addTariff(tariffsDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/{tariffId}")
    public HttpEntity<?> editTariff(@PathVariable Integer tariffId, @RequestBody TariffsDTO tariffsDTO) {

        ApiResponse apiResponse = tariffsService.editTariff(tariffId, tariffsDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping(value = "/{tariffId}")
    public HttpEntity<?> deleteTariff(@PathVariable Integer tariffId) {

        ApiResponse apiResponse = tariffsService.deleteTariff(tariffId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
