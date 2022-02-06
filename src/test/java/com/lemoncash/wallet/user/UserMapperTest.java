package com.lemoncash.wallet.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Test
    public void testUserMapperWithNullUserReturnsNull() {
        assertNull(UserMapper.userDTOToUser(null));
    }

    @Test
    public void testUserMapperReturnsCorrectEntity() {
        String firstName = "ketchup";
        String lastName = "serra";
        String username = "ketchup29";
        String password = "holi";
        String email = "ketchup@gmail.com";

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setEmail(email);

        User user = UserMapper.userDTOToUser(userDTO);

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
    }
}
