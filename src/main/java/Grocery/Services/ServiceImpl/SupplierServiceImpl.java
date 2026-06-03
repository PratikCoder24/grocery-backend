package Grocery.Services.ServiceImpl;

import Grocery.DTO.RequestDTO.SupplierRequestDTO;
import Grocery.DTO.ResponseDTO.SupplierResponseDTO;
import Grocery.Entities.Purchases;
import Grocery.Entities.Supplier;
import Grocery.Exception.ResourceNotFoundException;
import Grocery.Mapper.SupplierMapper;
import Grocery.Repository.SupplierRepository;
import Grocery.Services.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierResponseDTO addSupplier(SupplierRequestDTO add) {
        Supplier supplier = new Supplier();
        supplier.setName(add.getName());
        supplier.setPhone(add.getPhone());
        supplier.setEmail(add.getEmail());

        Supplier savedSupplier = supplierRepository.save(supplier);
        return SupplierMapper.mapToDTO(savedSupplier);
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long id,SupplierRequestDTO update) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        supplier.setName(update.getName());
        supplier.setPhone(update.getPhone());
        supplier.setEmail(update.getEmail());
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return SupplierMapper.mapToDTO(updatedSupplier);
    }

    @Override
    public SupplierResponseDTO deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        supplierRepository.delete(supplier);
        return SupplierMapper.mapToDTO(supplier);
    }

    @Override
    public SupplierResponseDTO getSupplierByName(String supplierName) {
        Supplier supplier = supplierRepository.findByName(supplierName)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with name: " + supplierName));
        return SupplierMapper.mapToDTO(supplier);

    }

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(SupplierMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Purchases> getPurchasesBySupplier(Long supplierId) {
       Supplier supplier = supplierRepository.findById(supplierId)
               .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + supplierId));

       return supplier.getPurchases();
    }
}
