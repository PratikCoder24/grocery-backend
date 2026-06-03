package Grocery.Mapper;

import Grocery.DTO.ResponseDTO.InventoryResponseDTO;
import Grocery.Entities.Inventory;
import Grocery.Entities.Product;

public class InventoryMapper {
    public static InventoryResponseDTO mapToDTO(Inventory inventory) {
        return new InventoryResponseDTO(
                inventory.getId(),
                inventory.getProduct().getName(),
                inventory.getProduct().getSku(),
                inventory.getQuantity(),
                inventory.getReorderLevel()

        );
    }
}
