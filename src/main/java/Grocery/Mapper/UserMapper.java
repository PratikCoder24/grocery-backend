package Grocery.Mapper;

import Grocery.DTO.ResponseDTO.UserResponseDTO;
import Grocery.Entities.User;

import java.util.List;

public class UserMapper {
    public static UserResponseDTO mapToDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                "ROLE_" + user.getRole().name()
        );
    }
}
