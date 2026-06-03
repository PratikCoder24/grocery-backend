package Grocery.Controller;

import Grocery.DTO.RequestDTO.SupplierRequestDTO;
import Grocery.DTO.ResponseDTO.SupplierResponseDTO;
import Grocery.Services.Service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers(){
        List<SupplierResponseDTO> responseDTO = supplierService.getAllSuppliers();
        return ResponseEntity.ok(responseDTO)   ;
    }

    @GetMapping("/name/{supplierName}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByName(@PathVariable String supplierName){
        SupplierResponseDTO responseDTO = supplierService.getSupplierByName(supplierName);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<SupplierResponseDTO> addSupplier(
            @Valid @RequestBody SupplierRequestDTO add
    ) {
        SupplierResponseDTO responseDTO = supplierService.addSupplier(add);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequestDTO update
    ){
        SupplierResponseDTO responseDTO = supplierService.updateSupplier(id, update);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> deleteSupplier(
            @PathVariable Long id
    ){
        SupplierResponseDTO responseDTO = supplierService.deleteSupplier(id);
        return ResponseEntity.ok(responseDTO);
    }
}
