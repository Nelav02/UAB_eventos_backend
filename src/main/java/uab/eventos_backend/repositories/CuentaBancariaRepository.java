package uab.eventos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uab.eventos_backend.models.CuentaBancariaEntity;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Long> {

    void deleteById(Long idCuenta);
}
