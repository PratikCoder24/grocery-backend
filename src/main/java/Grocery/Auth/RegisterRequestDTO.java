package Grocery.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
      @NotBlank(message = "username is required")
      private String username;

      @NotBlank(message = "email is required")
      private String email;

      @NotBlank(message = "password is required")
      private String password;

}
