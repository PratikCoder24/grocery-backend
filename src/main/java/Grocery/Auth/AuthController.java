package Grocery.Auth;

import Grocery.Services.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody RegisterRequestDTO registerRequestDTO
    ){
      AuthResponseDTO response = authService.register(registerRequestDTO);
      return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO
    ){
        AuthResponseDTO response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/promote/{id}")
    public ResponseEntity<Void> promote(
            @PathVariable Long id
    ){
        userService.promoteToAdmin(id);
        return ResponseEntity.ok().build();
    }
}
