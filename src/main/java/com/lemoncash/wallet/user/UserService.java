package com.lemoncash.wallet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(UserDTO user) {
        return userRepository.save(userMapper.userDTOToUser(user));
    }

}
