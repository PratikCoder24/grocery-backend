package Grocery.Mapper;

import Grocery.DTO.ResponseDTO.PurchaseDetailResponseDTO;
import Grocery.DTO.ResponseDTO.PurchaseResponseDTO;
import Grocery.Entities.Purchases;
import Grocery.PurchaseStatus;

import java.util.List;

public class PurchaseMapper {
    public static PurchaseResponseDTO mapToDTO(Purchases purchases) {
                 List<PurchaseDetailResponseDTO> items = purchases.getPurchaseDetails()
                         .stream()
                         .map(detail -> new PurchaseDetailResponseDTO(
                                 detail.getProduct().getName(),
                                 detail.getQuantity(),
                                 detail.getUnitCost(),
                                 detail.getQuantity() * detail.getUnitCost()
                         ))
                         .toList();
        return new PurchaseResponseDTO(
                purchases.getId(),
                purchases.getStatus().name(),
                purchases.getOrderDate(),
                purchases.getTotalAmount(),
                purchases.getSupplier().getName(),
                items
        );

    }
}
