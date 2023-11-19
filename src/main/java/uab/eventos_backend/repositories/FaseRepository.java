package uab.eventos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uab.eventos_backend.models.EFase;
import uab.eventos_backend.models.FaseEntity;

import java.util.Optional;

@Repository
public interface FaseRepository extends JpaRepository<FaseEntity, Long> {

    Optional<FaseEntity> findByName(EFase name);
}
