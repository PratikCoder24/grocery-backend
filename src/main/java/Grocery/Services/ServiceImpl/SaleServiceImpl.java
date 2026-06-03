package Grocery.Services.ServiceImpl;

import Grocery.DTO.RequestDTO.SaleDetailRequestDTO;
import Grocery.DTO.RequestDTO.SaleRequestDTO;
import Grocery.DTO.ResponseDTO.MonthlySalesResponseDTO;
import Grocery.DTO.ResponseDTO.SaleResponseDTO;
import Grocery.Entities.Inventory;
import Grocery.Entities.Product;
import Grocery.Entities.Sale;
import Grocery.Entities.SaleDetails;
import Grocery.Exception.ResourceNotFoundException;
import Grocery.Mapper.SaleMapper;
import Grocery.Repository.InventoryRepository;
import Grocery.Repository.ProductRepository;
import Grocery.Repository.SaleRepository;
import Grocery.Services.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public SaleResponseDTO createSale(SaleRequestDTO createSale) {
        Sale sale = new Sale();
        sale.setSaleDate(LocalDateTime.now().withNano(0));
        List<SaleDetails> detail = new ArrayList<>();

        double totalAmount = 0;
        for (SaleDetailRequestDTO item : createSale.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + item.getProductId()));

            Inventory inventory = product.getInventory();

            if (inventory == null) {
                throw new ResourceNotFoundException("Inventory not found");
            }

            if (inventory.getQuantity() < item.getQuantity()) {
                throw new ResourceNotFoundException("Insufficient stock for product id: " + item.getProductId());
            }

            inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
            inventoryRepository.save(inventory);

            SaleDetails details = new SaleDetails();
            details.setProduct(product);
            details.setQuantity(item.getQuantity());
            details.setUnitPrice(product.getSellPrice());
            details.setSale(sale);

            detail.add(details);

            totalAmount += item.getQuantity() * product.getSellPrice();
        }
     sale.setSaleDetails(detail);
     sale.setTotalAmount(totalAmount);

     Sale savedSale = saleRepository.save(sale);
      return   SaleMapper.mapToDTO(savedSale);

    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(SaleMapper::mapToDTO)
                .toList();
    }

    @Override
    public List<MonthlySalesResponseDTO> getMonthlySales() {
        List<Object[]> data = saleRepository.getMonthlySales();

        return data.stream()
                .map(obj -> new MonthlySalesResponseDTO(
                        Month.of(((Number) obj[0]).intValue())
                                .getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        ((Number) obj[1]).doubleValue()
                ))
                .toList();
    }
}
