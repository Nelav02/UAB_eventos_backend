package uab.eventos_backend.services;

import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.request.RegisterUserEntity;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {

    Optional<UserEntity> getUserByEmail(String email) throws UserNotFoundException;

    List<UserEntity> getAllUsers();

    Optional<UserEntity> updateUser(RegisterUserEntity request, Long id) throws UserNotFoundException;

    void deleteUser(Long id);
}
