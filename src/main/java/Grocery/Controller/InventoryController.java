package Grocery.Controller;

import Grocery.DTO.RequestDTO.InventoryRequestDTO;
import Grocery.DTO.ResponseDTO.InventoryResponseDTO;
import Grocery.Services.Service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<InventoryResponseDTO>> getAllInventory(){
        List<InventoryResponseDTO> responseDTO = inventoryService.getInventory();
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryResponseDTO>> getLowStockInventory(){
        List<InventoryResponseDTO> responseDTO = inventoryService.getLowStockInventory();
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/add-stock/{id}")
    public ResponseEntity<InventoryResponseDTO> addStock(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequestDTO inventoryRequestDTO){
        InventoryResponseDTO responseDTO = inventoryService.addStock(id, inventoryRequestDTO.getQuantity());
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/reduce-stock/{id}")
    public ResponseEntity<InventoryResponseDTO> reduceStock(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequestDTO inventoryRequestDTO){
        InventoryResponseDTO responseDTO = inventoryService.reduceStock(id, inventoryRequestDTO.getQuantity());
        return ResponseEntity.ok(responseDTO);
    }

}


