package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uab.eventos_backend.exceptions.UserFoundException;
import uab.eventos_backend.models.*;
import uab.eventos_backend.repositories.RoleRepository;
import uab.eventos_backend.repositories.UserRepository;
import uab.eventos_backend.response.AuthResponse;
import uab.eventos_backend.request.LoginUserEntity;
import uab.eventos_backend.request.RegisterUserEntity;
import uab.eventos_backend.security.JwtService;
import uab.eventos_backend.services.AuthService;

import java.security.Principal;
import java.util.*;
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
    public AuthResponse registerUser(RegisterUserEntity userDto) throws Exception {

        Set<RoleEntity> roles = userDto.getRoles().stream()
                .map(role ->{
                    Optional<RoleEntity> roleExistente = this.roleRepository.findByName(ERole.valueOf(role));
                    return roleExistente.orElseGet(() -> RoleEntity.builder()
                            .name(ERole.valueOf(role))
                            .build());
                })
                .collect(Collectors.toSet());

        UserEntity newUser = UserEntity.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nombre((userDto.getNombre()))
                .apellidos(userDto.getApellidos())
                .telefono(userDto.getTelefono())
                .genero(EGenero.valueOf(userDto.getGenero()))
                .perfil(userDto.getPerfil())
                .habilitado(true)
                .roles(roles)
                .eventos(List.of())
                .build();

        if (userDto.getBanco() == null && userDto.getCuenta() == null) {
            newUser.setCuentasBancarias(List.of());
        } else if (Objects.equals(userDto.getBanco(), "") && Objects.equals(userDto.getCuenta(), "")) {
            newUser.setCuentasBancarias(List.of());
        }
        else {
            newUser.setCuentasBancarias(List.of(CuentaBancariaEntity.builder()
                    .banco(userDto.getBanco())
                    .cuenta(userDto.getCuenta())
                    .user(newUser)
                    .build()));
        }

        if (this.userRepository.existsByEmail(newUser.getEmail())) {
            throw new UserFoundException("UserEnity con email '" + newUser.getEmail() + "' ya existe.");
        }

        this.userRepository.save(newUser);

        String jwtToken = this.jwtService.generateToken(newUser);

        return AuthResponse.builder()
                .message("Autenticacion correcta")
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse loginUser(LoginUserEntity request) {
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
