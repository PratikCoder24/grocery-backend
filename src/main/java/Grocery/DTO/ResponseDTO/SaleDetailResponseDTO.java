package Grocery.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailResponseDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;
}
