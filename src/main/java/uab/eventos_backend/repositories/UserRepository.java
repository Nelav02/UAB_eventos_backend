package uab.eventos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uab.eventos_backend.models.CuentaBancariaEntity;
import uab.eventos_backend.models.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
            SELECT u.cuentasBancarias FROM UserEntity u WHERE u.id = :userId
            """)
    List<CuentaBancariaEntity> findBankAccountsByUserId(@Param("userId") Long userId);
}
