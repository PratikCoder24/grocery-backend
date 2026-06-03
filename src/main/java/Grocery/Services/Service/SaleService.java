package Grocery.Services.Service;

import Grocery.DTO.RequestDTO.SaleRequestDTO;
import Grocery.DTO.ResponseDTO.MonthlySalesResponseDTO;
import Grocery.DTO.ResponseDTO.SaleResponseDTO;

import java.util.List;

public interface SaleService {
    SaleResponseDTO createSale(SaleRequestDTO createSale);

    List<SaleResponseDTO> getAllSales();

    List<MonthlySalesResponseDTO> getMonthlySales();
}
