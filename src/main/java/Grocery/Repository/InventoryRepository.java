package Grocery.Repository;

import Grocery.Entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
   Optional<Inventory> findById(Long id);
  @Query("Select i from Inventory i where i.quantity <= i.reorderLevel")
  List<Inventory> findLowStockInventory();
}
