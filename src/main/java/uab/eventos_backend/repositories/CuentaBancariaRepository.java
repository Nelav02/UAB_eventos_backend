package uab.eventos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uab.eventos_backend.models.CuentaBancariaEntity;

import java.util.List;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Long> {

    @Query("""
            SELECT u.cuentasBancarias FROM UserEntity u WHERE u.id = :userId
            """)
    List<CuentaBancariaEntity> findBankAccountsByUserId(@Param("userId") Long userId);
}
