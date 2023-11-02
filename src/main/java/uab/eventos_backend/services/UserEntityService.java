package uab.eventos_backend.services;

import uab.eventos_backend.models.UserEntity;

import java.util.List;

public interface UserEntityService {

    List<UserEntity> getAllUsers();

    void deleteUser(Long id);
}
