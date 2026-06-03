package Grocery.Controller;

import Grocery.DTO.RequestDTO.PurchaseRequestDTO;
import Grocery.DTO.ResponseDTO.PurchaseResponseDTO;
import Grocery.PurchaseStatus;
import Grocery.Services.Service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseResponseDTO>> getAllPurchases(){
        List<PurchaseResponseDTO> responseDTO = purchaseService.getAllPurchases();
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<PurchaseResponseDTO> createPurchase(
            @Valid @RequestBody PurchaseRequestDTO createPurchase
    ){
        PurchaseResponseDTO responseDTO = purchaseService.createPurchase(createPurchase);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<PurchaseResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam PurchaseStatus status
            ){
        PurchaseResponseDTO responseDTO = purchaseService.updateStatus(id, status);
        return ResponseEntity.ok(responseDTO);
    }
}
