package Grocery.Services.Service;

import Grocery.Auth.AuthResponseDTO;
import Grocery.DTO.ResponseDTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    void promoteToAdmin(Long id);

    List<UserResponseDTO> getAllUsers();
}
