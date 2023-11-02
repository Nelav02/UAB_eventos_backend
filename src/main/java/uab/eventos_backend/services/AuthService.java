package uab.eventos_backend.services;

import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.request.AuthResponse;
import uab.eventos_backend.request.LoginUserEntityDTO;
import uab.eventos_backend.request.RegisterUserEntityDTO;

import java.security.Principal;

public interface AuthService {

    AuthResponse registerUser(RegisterUserEntityDTO request) throws Exception;

    AuthResponse loginUser(LoginUserEntityDTO request);

    UserEntity getCurrentUser(Principal principal);
}
