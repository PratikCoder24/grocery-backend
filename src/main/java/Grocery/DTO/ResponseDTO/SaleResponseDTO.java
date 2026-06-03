package Grocery.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SaleResponseDTO {
    private Long id;
    private double totalAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String saleDate;

    private List<SaleDetailResponseDTO> items;
}
