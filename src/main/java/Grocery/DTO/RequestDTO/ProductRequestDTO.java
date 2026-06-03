package Grocery.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "product name is required")
    private String productName;

    @NotNull(message = "price is required")
    private Double costPrice;

    @NotNull(message = "price is required")
    private Double sellPrice;

    @NotBlank(message = "SKU is required")
    private String sku;

    @NotNull(message = "category name is required")
    private Long categoryId;

    @NotNull(message = "quantity is required")
    @PositiveOrZero(message = "quantity must be zero or positive")
    private Integer quantity;

    @NotNull(message = "reorder-level is required")
    @PositiveOrZero(message = "reorder-level must be zero or positive")
    private Integer reorderLevel;
}
