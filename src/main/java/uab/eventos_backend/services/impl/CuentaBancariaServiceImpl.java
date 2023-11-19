package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uab.eventos_backend.models.CuentaBancariaEntity;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.repositories.CuentaBancariaRepository;
import uab.eventos_backend.repositories.UserRepository;
import uab.eventos_backend.request.CuentaBancariaDTO;
import uab.eventos_backend.services.CuentaBancariaService;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CuentaBancariaEntity> getAllCuentasBancarias(Long id) {
        return this.cuentaBancariaRepository.findBankAccountsByUserId(id);
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
