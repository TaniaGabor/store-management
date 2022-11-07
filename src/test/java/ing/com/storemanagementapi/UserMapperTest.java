package ing.com.storemanagementapi;

import ing.com.storemanagementapi.dto.UserDto;
import ing.com.storemanagementapi.enums.RoleEnum;
import ing.com.storemanagementapi.mapper.UserMapper;
import ing.com.storemanagementapi.model.Role;
import ing.com.storemanagementapi.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    @Test
    void dataFromUserTest() {
        //GIVEN
        long id = 7;
        User expected = createUser(id);

        //WHEN
        UserDto actual = UserMapper.INSTANCE.userToUserDto(expected);

        //THEN
        assertEquality(expected, actual);
    }

    @Test
    void userFromDtoTest() {
        //GIVEN
        long id = 3;
        UserDto expected = createUserDto(id);

        //WHEN
        User actual = UserMapper.INSTANCE.userDtoToUser(expected);

        //THEN
        assertEquality(expected, actual);
    }

    private User createUser(long id) {
        User user = new User();
        user.setId(id);
        user.setEmail("user@email.com");
        user.setPassword("sTr0nGp45w0rd!");
        user.setUsername("user");
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(2, RoleEnum.ROLE_ADMIN));
        user.setRoles(roles);
        return user;
    }

    private UserDto createUserDto(long id) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(2, RoleEnum.ROLE_ADMIN));
        return new UserDto(1, "userDto", "userDto", "userDTO@yahoo.com", "ahsdgaydga", roles);
    }

    private void assertEquality(User expected, UserDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getRoles(), actual.getRoles());
    }

    private void assertEquality(UserDto expected, User actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getRoles(), actual.getRoles());
    }

}
