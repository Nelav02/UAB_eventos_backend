package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.EGenero;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.repositories.UserRepository;
import uab.eventos_backend.request.RegisterUserEntityDTO;
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
    public Optional<UserEntity> updateUser(RegisterUserEntityDTO request, Long id) throws UserNotFoundException {
        UserEntity usuario = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario con id: '" + id + "' no encontrado."));

        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setTelefono(request.getTelefono());
        usuario.setGenero(EGenero.valueOf(request.getGenero()));
        usuario.setBanco(request.getBanco());
        usuario.setCuentasBancarias(request.getCuentasBancarias());

        return Optional.of(this.userRepository.save(usuario));
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
