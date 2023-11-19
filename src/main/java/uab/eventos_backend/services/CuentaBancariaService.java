package uab.eventos_backend.services;

import uab.eventos_backend.models.CuentaBancariaEntity;
import uab.eventos_backend.request.CuentaBancariaDTO;

import java.util.List;

public interface CuentaBancariaService {

    List<CuentaBancariaEntity> getAllCuentasBancarias(Long id);

    boolean agregarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO);

    void deleteCuentaBancaria(Long idCuenta);
}
