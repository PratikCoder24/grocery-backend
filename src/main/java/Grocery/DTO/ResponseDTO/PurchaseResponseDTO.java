package Grocery.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {

    private Long purchaseId;

    private String status;

    private LocalDateTime purchaseDate;

    private double totalAmount;

    private String supplierName;

    private List<PurchaseDetailResponseDTO> items;
}
