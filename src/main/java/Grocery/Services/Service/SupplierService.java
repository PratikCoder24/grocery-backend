package Grocery.Services.Service;

import Grocery.DTO.RequestDTO.SupplierRequestDTO;
import Grocery.DTO.ResponseDTO.SupplierResponseDTO;
import Grocery.Entities.Purchases;

import java.util.List;

public interface SupplierService {
    SupplierResponseDTO addSupplier(SupplierRequestDTO add);
    SupplierResponseDTO updateSupplier(Long id,SupplierRequestDTO update);
    SupplierResponseDTO deleteSupplier(Long id);
    SupplierResponseDTO getSupplierByName(String supplierName);
    List<SupplierResponseDTO> getAllSuppliers();
    List<Purchases> getPurchasesBySupplier(Long supplierId);
}
