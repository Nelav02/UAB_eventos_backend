package uab.eventos_backend.services;

import uab.eventos_backend.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {

    Optional<UserEntity> getUserByEmail(String email);

    List<UserEntity> getAllUsers();

    void deleteUser(Long id);
}
