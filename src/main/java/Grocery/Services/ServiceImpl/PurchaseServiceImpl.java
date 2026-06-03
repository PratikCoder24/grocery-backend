package Grocery.Services.ServiceImpl;

import Grocery.DTO.RequestDTO.PurchaseDetailRequestDTO;
import Grocery.DTO.RequestDTO.PurchaseRequestDTO;
import Grocery.DTO.ResponseDTO.PurchaseDetailResponseDTO;
import Grocery.DTO.ResponseDTO.PurchaseResponseDTO;
import Grocery.Entities.*;
import Grocery.Exception.ResourceNotFoundException;
import Grocery.Mapper.PurchaseMapper;
import Grocery.PurchaseStatus;
import Grocery.Repository.InventoryRepository;
import Grocery.Repository.ProductRepository;
import Grocery.Repository.PurchaseRepository;
import Grocery.Repository.SupplierRepository;
import Grocery.Services.Service.PurchaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchasesRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchasesRepository, ProductRepository productRepository, InventoryRepository inventoryRepository, SupplierRepository supplierRepository) {
        this.purchasesRepository = purchasesRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    @Override
    public PurchaseResponseDTO createPurchase(PurchaseRequestDTO createPurchase) {
         Supplier supplier = supplierRepository.findById(createPurchase.getSupplierId())
                 .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + createPurchase.getSupplierId()));

         Purchases purchase = new Purchases();
         purchase.setSupplier(supplier);
         purchase.setOrderDate(LocalDateTime.now());
         purchase.setStatus(PurchaseStatus.PENDING);
         List<PurchaseDetails> details = new ArrayList<>();
         double total = 0;


         for(PurchaseDetailRequestDTO item: createPurchase.getItems()){
             Product product = productRepository.findById(item.getProductId())
                     .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + item.getProductId()));


             if (product.getInventory() == null) {
                 throw new ResourceNotFoundException("Inventory not assigned to product");
             }
             Inventory inventory = product.getInventory();

             inventory.setQuantity(inventory.getQuantity() + item.getQuantity());
             inventoryRepository.save(inventory);

             PurchaseDetails detail = new PurchaseDetails();

             detail.setPurchases(purchase);
             detail.setProduct(product);
             detail.setQuantity(item.getQuantity());
             detail.setUnitCost(item.getUnitCost());

             total += item.getQuantity() * item.getUnitCost();
             details.add(detail);

         }

         purchase.setPurchaseDetails(details);
         purchase.setTotalAmount(total);

         Purchases savedPurchase = purchasesRepository.save(purchase);
         return  PurchaseMapper.mapToDTO(savedPurchase);
    }

    @Override
    public List<PurchaseResponseDTO> getAllPurchases() {
        return purchasesRepository.findAll()
                .stream()
                .map(PurchaseMapper :: mapToDTO)
                .toList();
    }

    @Override
    public PurchaseResponseDTO updateStatus(Long id, PurchaseStatus status) {
        Purchases purchase = purchasesRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Purchase not found with id: " + id));

        purchase.setStatus(status);

        Purchases updated =  purchasesRepository.save(purchase);
        return PurchaseMapper.mapToDTO(updated);

    }

}
