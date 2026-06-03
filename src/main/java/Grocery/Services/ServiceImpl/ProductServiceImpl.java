package Grocery.Services.ServiceImpl;

import Grocery.DTO.RequestDTO.ProductRequestDTO;
import Grocery.DTO.ResponseDTO.ProductResponseDTO;
import Grocery.Entities.Category;
import Grocery.Entities.Inventory;
import Grocery.Entities.Product;
import Grocery.Exception.DuplicateResourceException;
import Grocery.Exception.ResourceNotFoundException;
import Grocery.Mapper.ProductMapper;
import Grocery.Repository.CategoryRepository;
import Grocery.Repository.InventoryRepository;
import Grocery.Repository.ProductRepository;
import Grocery.Services.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, InventoryRepository inventoryRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO addProduct) {
        Category category = categoryRepository.findById(addProduct.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + addProduct.getCategoryId()));


          if (productRepository.existsByName(addProduct.getProductName())){
              throw new DuplicateResourceException(
                      "Product name already exists"
              );
          }

          Inventory inventory = new Inventory();
          inventory.setQuantity(addProduct.getQuantity());
          inventory.setReorderLevel(addProduct.getReorderLevel());

          Product products = ProductMapper.mapToProduct(addProduct);
          products.setCategory(category);
          products.setInventory(inventory);
         Product savedProduct =  productRepository.save(products);
          return ProductMapper.mapToDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO updatedProduct) {
         Product product = productRepository.findById(id).
                 orElseThrow(() -> new ResourceNotFoundException("Product not found"));

         product.setName(updatedProduct.getProductName());
         product.setSku(updatedProduct.getSku());
         product.setCostPrice(updatedProduct.getCostPrice());
         product.setSellPrice(updatedProduct.getSellPrice());
         productRepository.save(product);
         return ProductMapper.mapToDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProductResponseDTO deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
        return ProductMapper.mapToDTO(product);
    }
}
