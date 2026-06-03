package Grocery.Services.ServiceImpl;

import Grocery.DTO.ResponseDTO.InventoryResponseDTO;
import Grocery.Entities.Inventory;
import Grocery.Entities.Product;
import Grocery.Exception.ResourceNotFoundException;
import Grocery.Mapper.InventoryMapper;
import Grocery.Repository.InventoryRepository;
import Grocery.Repository.ProductRepository;
import Grocery.Services.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<InventoryResponseDTO> getInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(InventoryMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDTO getInventoryByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for product id: " + productId));
        if (product.getInventory() == null) {
            throw new ResourceNotFoundException(
                    "Inventory not found for product id: " + productId);
        }
        return InventoryMapper.mapToDTO(product.getInventory());
    }

    @Override
    public List<InventoryResponseDTO> getLowStockInventory() {
        return inventoryRepository.findLowStockInventory()
                .stream()
                .map(InventoryMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDTO addStock(Long productId,Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for product id: " + productId));

        if (product.getInventory() == null) {
            throw new ResourceNotFoundException("Inventory not found for product id: " + productId);
        }

        Inventory inventory = product.getInventory();
        inventory.setQuantity(inventory.getQuantity() + quantity);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.mapToDTO(savedInventory);
    }

    @Override
    public InventoryResponseDTO reduceStock(Long productId,Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for product id: " + productId));

        if (product.getInventory() == null) {
            throw new ResourceNotFoundException("Inventory not found for product id: " + productId);
        }

        Inventory inventory = product.getInventory();
        inventory.setQuantity(inventory.getQuantity() - quantity);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.mapToDTO(savedInventory);
    }
}
