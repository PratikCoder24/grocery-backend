package Grocery.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {
    private Long id;
    private String productName;
    private String sku;
    private Integer quantity;
    private Integer reorderLevel;
}
