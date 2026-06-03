package Grocery.Services.Service;

import Grocery.DTO.RequestDTO.PurchaseRequestDTO;
import Grocery.DTO.ResponseDTO.PurchaseResponseDTO;
import Grocery.PurchaseStatus;

import java.util.List;

public interface PurchaseService {
    PurchaseResponseDTO createPurchase(PurchaseRequestDTO createPurchase);

    List<PurchaseResponseDTO> getAllPurchases();

    PurchaseResponseDTO updateStatus(Long id, PurchaseStatus status);

}
