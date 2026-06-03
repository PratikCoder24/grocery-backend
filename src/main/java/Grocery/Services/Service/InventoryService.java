package Grocery.Services.Service;

import Grocery.DTO.RequestDTO.InventoryRequestDTO;
import Grocery.DTO.ResponseDTO.InventoryResponseDTO;
import Grocery.Entities.Inventory;

import java.util.List;

public interface InventoryService {

    List<InventoryResponseDTO> getInventory();

    InventoryResponseDTO getInventoryByProductId(Long  productId);

    List<InventoryResponseDTO> getLowStockInventory();

    InventoryResponseDTO addStock(Long  productId,Integer quantity);

    InventoryResponseDTO reduceStock(Long productId,  Integer quantity);

}
