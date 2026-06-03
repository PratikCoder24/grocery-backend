package Grocery.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PurchaseDetailResponseDTO {
    private String productName;

    private Integer quantity;

    private double unitCost;

    private double subtotal;
}
