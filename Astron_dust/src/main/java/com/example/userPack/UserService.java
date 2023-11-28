package com.example.userPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void signUp(String UID, String password, String checkPassword) {
        if (userRepository.findByUID(UID).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        if (!password.equals(checkPassword)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        Users user = new Users(UID, password);
        user.setUID(UID);
        user.setPassword(password);

        userRepository.save(user);
    }

    public boolean checkUID(String UID) {
        return userRepository.findByUID(UID).isPresent();
    }
}
