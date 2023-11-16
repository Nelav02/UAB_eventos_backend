package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.CuentaBancariaEntity;
import uab.eventos_backend.models.EGenero;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.repositories.CuentaBancariaRepository;
import uab.eventos_backend.repositories.UserRepository;
import uab.eventos_backend.request.CuentaBancariaDTO;
import uab.eventos_backend.request.RegisterUserEntity;
import uab.eventos_backend.services.UserEntityService;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;


    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> updateUser(RegisterUserEntity request, Long id) throws UserNotFoundException {
        UserEntity usuario = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario con id: '" + id + "' no encontrado."));

        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setTelefono(request.getTelefono());
        usuario.setGenero(EGenero.valueOf(request.getGenero()));

        return Optional.of(this.userRepository.save(usuario));
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<CuentaBancariaEntity> getAllCuentasBancarias(Long id) {
        return this.userRepository.findBankAccountsByUserId(id);
    }

    @Override
    public boolean agregarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) {
        Optional<UserEntity> user = this.userRepository.findById(cuentaBancariaDTO.getUserId());

        if (user.isPresent()) {
            CuentaBancariaEntity nuevaCuenta = CuentaBancariaEntity.builder()
                    .banco(cuentaBancariaDTO.getBanco())
                    .cuenta(cuentaBancariaDTO.getCuenta())
                    .user(user.get())
                    .build();

            user.get().getCuentasBancarias().add(nuevaCuenta);

            this.userRepository.save(user.get());
            return true;
        }
        return false;
    }

    @Override
    public void deleteCuentaBancaria(Long idCuenta) {
        this.cuentaBancariaRepository.deleteById(idCuenta);
    }


}
