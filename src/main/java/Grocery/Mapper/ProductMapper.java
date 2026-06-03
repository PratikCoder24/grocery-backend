package Grocery.Mapper;

import Grocery.DTO.RequestDTO.ProductRequestDTO;
import Grocery.DTO.ResponseDTO.ProductResponseDTO;
import Grocery.Entities.Product;

public class ProductMapper {
    public static ProductResponseDTO mapToDTO(Product product) {
       return new ProductResponseDTO(
               product.getId(),
               product.getName(),
               product.getSku(),
               product.getCostPrice(),
               product.getSellPrice(),
               product.getInventory().getQuantity(),
               product.getInventory().getReorderLevel(),
               product.getCategory().getName(),
               product.getCategory().getId()
       );
    }

    public static Product mapToProduct(ProductRequestDTO productRequest) {
        Product product = new Product();
        product.setName(productRequest.getProductName());
        product.setSku(productRequest.getSku());
        product.setCostPrice(productRequest.getCostPrice());
        product.setSellPrice(productRequest.getSellPrice());
        return  product;
    }

}
