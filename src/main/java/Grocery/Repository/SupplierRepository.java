package Grocery.Repository;

import Grocery.Entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findById(Long id);

    Optional<Supplier> findByName(String name);
}
