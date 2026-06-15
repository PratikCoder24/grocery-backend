package Grocery.DTO.RequestDTO;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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

public class SaleRequestDTO {
    @NotNull(message = "items are required")
    @NotEmpty(message = " Sale must have at least 1 item")
    @Valid
    private List<SaleDetailRequestDTO> items;
}
