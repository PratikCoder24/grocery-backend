package Grocery.Services.ServiceImpl;

import Grocery.Auth.AuthResponseDTO;
import Grocery.Entities.User;
import Grocery.Enum.Role;
import Grocery.Repository.UserRepository;
import Grocery.Services.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void promoteToAdmin(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setRole(Role.ADMIN);
        userRepository.save(user);

    }
}
