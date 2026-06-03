package Grocery.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequestDTO {
    @NotBlank(message = "Supplier name is required")
    private String name;

    @NotBlank(message = "Contact information is required")
    private String phone;

    @NotBlank(message = "email is required")
    private String email;
}
