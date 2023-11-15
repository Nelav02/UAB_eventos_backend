package uab.eventos_backend.services;

import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.response.AuthResponse;
import uab.eventos_backend.request.LoginUserEntity;
import uab.eventos_backend.request.RegisterUserEntity;

import java.security.Principal;

public interface AuthService {

    AuthResponse registerUser(RegisterUserEntity request) throws Exception;

    AuthResponse loginUser(LoginUserEntity request);

    UserEntity getCurrentUser(Principal principal);
}
