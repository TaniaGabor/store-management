package ing.com.storemanagementapi.service;

import ing.com.storemanagementapi.dto.ProductDto;
import ing.com.storemanagementapi.dto.UserDto;
import ing.com.storemanagementapi.exception.ApiEntityNotFoundException;
import ing.com.storemanagementapi.exception.ApiUsernameNotFoundException;
import ing.com.storemanagementapi.mapper.UserMapper;
import ing.com.storemanagementapi.model.Product;
import ing.com.storemanagementapi.model.User;
import ing.com.storemanagementapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    final UserMapper mapper = UserMapper.INSTANCE;

    @Autowired
    UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .parallelStream()
                .map(mapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiEntityNotFoundException("user", id));
        return mapper.userToUserDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(mapper.userDtoToUser(userDto));
        return mapper.userToUserDto(user);
    }

    public UserDto updateUser(long id, UserDto userDto) {
        User user = mapper.userDtoToUser(userDto);
        user.setId(id);
        userRepository.findById(id).
                ifPresentOrElse(
                        (val) -> {
                            val.setEmail(user.getEmail());
                            val.setName(user.getName());
                            val.setRoles(user.getRoles());
                            val.setUsername(user.getUsername());
                            val.setPassword(user.getPassword());
                        },
                        () -> {
                            userRepository.save(user);
                        }
                );
        return mapper.userToUserDto(user);
    }

    public void deleteUser(long id) {
        userRepository.findById(id)
                .ifPresentOrElse(
                        (user) -> {
                            userRepository.delete(user);
                        },
                        () -> {
                            throw new ApiEntityNotFoundException("User", id);
                        }
                );
    }

    public boolean userExistsWithUsernameAndPassword(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    public UserDto getUserByUsername(String username) {
        return mapper.userToUserDto(userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiUsernameNotFoundException(username)));
    }
}