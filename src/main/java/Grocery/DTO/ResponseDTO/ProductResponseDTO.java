package Grocery.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private long id;

    private String name;

    private String sku;

    private double costPrice;

    private double sellingPrice;

    private Integer quantity;

    private Integer reorderLevel;

    private String categoryName;

    private Long categoryId;
}
