package com.lemoncash.wallet.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder().firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail()).build();

    }

}
