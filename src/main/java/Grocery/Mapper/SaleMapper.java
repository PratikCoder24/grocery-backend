package Grocery.Mapper;

import Grocery.DTO.ResponseDTO.SaleDetailResponseDTO;
import Grocery.DTO.ResponseDTO.SaleResponseDTO;
import Grocery.Entities.Sale;

import java.util.List;

public class SaleMapper {
    public static SaleResponseDTO mapToDTO(Sale sale) {
                List<SaleDetailResponseDTO> items = sale.getSaleDetails()
                        .stream()
                        .map(detail -> new SaleDetailResponseDTO(
                                detail.getProduct().getId(),
                                detail.getProduct().getName(),
                                detail.getQuantity(),
                                detail.getUnitPrice()
                        ))
                        .toList();
        return new SaleResponseDTO(
                sale.getId(),
                sale.getTotalAmount(),
                sale.getSaleDate().toString(),
                items
        );
    }
}
