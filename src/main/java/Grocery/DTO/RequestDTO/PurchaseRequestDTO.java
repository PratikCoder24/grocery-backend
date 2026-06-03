package Grocery.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDTO {
    @NotNull(message = "supplierId is required")
    private Long supplierId;

    private List<PurchaseDetailRequestDTO> items;
}
