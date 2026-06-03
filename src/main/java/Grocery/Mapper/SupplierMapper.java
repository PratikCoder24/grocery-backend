package Grocery.Mapper;

import Grocery.DTO.ResponseDTO.SupplierResponseDTO;
import Grocery.Entities.Supplier;

public class SupplierMapper {
    public static SupplierResponseDTO mapToDTO(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getPhone(),
                supplier.getEmail()
        );
    }
}
