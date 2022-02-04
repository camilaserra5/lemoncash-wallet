package com.lemoncash.wallet.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

}
