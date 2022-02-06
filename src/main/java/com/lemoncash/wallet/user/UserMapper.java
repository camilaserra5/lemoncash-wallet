package com.lemoncash.wallet.user;

import com.lemoncash.wallet.util.PasswordEncrypter;
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
                .password(PasswordEncrypter.encrypt(userDTO.getPassword()))
                .email(userDTO.getEmail()).build();

    }

}
