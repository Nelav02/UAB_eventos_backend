package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.repositories.UserRepository;
import uab.eventos_backend.services.UserEntityService;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
