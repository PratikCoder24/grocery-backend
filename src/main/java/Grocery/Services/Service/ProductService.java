package Grocery.Services.Service;

import Grocery.DTO.RequestDTO.ProductRequestDTO;
import Grocery.DTO.ResponseDTO.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO Product);

    ProductResponseDTO updateProduct(Long id,ProductRequestDTO updatedProduct);

    List<ProductResponseDTO> getProducts();

    ProductResponseDTO deleteProduct(Long id);
}
