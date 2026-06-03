package Grocery.Controller;

import Grocery.DTO.RequestDTO.ProductRequestDTO;
import Grocery.DTO.ResponseDTO.ProductResponseDTO;
import Grocery.Services.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> responseDTO = productService.getProducts();
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> addProducts(
            @Valid @RequestBody ProductRequestDTO productRequestDTO
    ){
        ProductResponseDTO responseDTO = productService.addProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProducts(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO productRequestDTO
    ){
        ProductResponseDTO responseDTO = productService.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProducts(@PathVariable Long id){
        ProductResponseDTO responseDTO = productService.deleteProduct(id);
        return ResponseEntity.ok(responseDTO);
    }

}
