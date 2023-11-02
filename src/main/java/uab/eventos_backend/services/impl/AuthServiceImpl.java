package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uab.eventos_backend.exceptions.UserFoundException;
import uab.eventos_backend.models.EGenero;
import uab.eventos_backend.models.ERole;
import uab.eventos_backend.models.RoleEntity;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.repositories.RoleRepository;
import uab.eventos_backend.repositories.UserRepository;
import uab.eventos_backend.request.AuthResponse;
import uab.eventos_backend.request.LoginUserEntityDTO;
import uab.eventos_backend.request.RegisterUserEntityDTO;
import uab.eventos_backend.security.JwtService;
import uab.eventos_backend.services.AuthService;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public AuthResponse registerUser(RegisterUserEntityDTO userDto) throws Exception {

        Set<RoleEntity> roles = userDto.getRoles().stream()
                .map(role ->{
                    Optional<RoleEntity> roleExistente = this.roleRepository.findByName(ERole.valueOf(role));
                    return roleExistente.orElseGet(() -> RoleEntity.builder()
                            .name(ERole.valueOf(role))
                            .build());
                })
                .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nombre((userDto.getNombre()))
                .apellidos(userDto.getApellidos())
                .telefono(userDto.getTelefono())
                .genero(EGenero.valueOf(userDto.getGenero()))
                .perfil(userDto.getPerfil())
                .banco(userDto.getBanco())
                .cuentasBancarias(userDto.getCuentasBancarias())
                .habilitado(true)
                .roles(roles)
                .build();

        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new UserFoundException("UserEnity con email '" + user.getEmail() + "' ya existe.");
        }

        this.userRepository.save(user);

        String jwtToken = this.jwtService.generateToken(user);

        return AuthResponse.builder()
                .message("Autenticacion correcta")
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse loginUser(LoginUserEntityDTO request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity user = this.userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        String jwtToken = this.jwtService.generateToken(user);

        return AuthResponse.builder()
                .message("Autenticacion correcta")
                .token(jwtToken)
                .build();
    }

    @Override
    public UserEntity getCurrentUser(Principal principal) {
        return (UserEntity) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
