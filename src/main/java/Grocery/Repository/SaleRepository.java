package Grocery.Repository;

import Grocery.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = """
SELECT 
    MONTH(s.sale_date) AS month,
    SUM(s.total_amount) AS totalSales
FROM sale s
GROUP BY MONTH(s.sale_date)
ORDER BY MONTH(s.sale_date)
""", nativeQuery = true)
    List<Object[]> getMonthlySales();
}
