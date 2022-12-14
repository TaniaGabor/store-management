package ing.com.storemanagementapi.controller;

import ing.com.storemanagementapi.dto.UserDto;
import ing.com.storemanagementapi.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") long id, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

}
