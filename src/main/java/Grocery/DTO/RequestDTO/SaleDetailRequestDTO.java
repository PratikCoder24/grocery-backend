package Grocery.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailRequestDTO {
   @NotNull(message = "productId is required")
   private Long productId;
   @NotNull(message = "quantity is required")
   @Positive(message = "quantity must have positive value")
   private Integer quantity;

}
