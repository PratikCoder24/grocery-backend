package Grocery.Controller;

import Grocery.DTO.RequestDTO.SaleRequestDTO;
import Grocery.DTO.ResponseDTO.MonthlySalesResponseDTO;
import Grocery.DTO.ResponseDTO.SaleResponseDTO;
import Grocery.Services.Service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/sale")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<SaleResponseDTO> createSale(
           @Valid @RequestBody SaleRequestDTO createSale
    ){
        SaleResponseDTO responseDTO = saleService.createSale(createSale);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        List<SaleResponseDTO> responseDTO = saleService.getAllSales();
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/monthly-sales")
    public ResponseEntity<List<MonthlySalesResponseDTO>> getMonthlySales(){
        List<MonthlySalesResponseDTO> responseDTO = saleService.getMonthlySales();
        return ResponseEntity.ok(responseDTO);
    }

}
